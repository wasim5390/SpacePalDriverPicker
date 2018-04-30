package com.spacepal.internal.app.source

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spacepal.internal.app.AnnotationExclusionStrategy
import com.spacepal.internal.app.SpacePalApplication
import com.spacepal.internal.app.event.LoginFailEvent
import com.spacepal.internal.app.model.response.TokenResponse
import com.spacepal.internal.app.util.PreferenceUtil
import com.spacepal.internal.app.util.Util
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitHelper private constructor(baseUrl: String) {
    var retrofit: Retrofit
    var api: API
        internal set

    init {

        val tokenResponse = PreferenceUtil.getInstance(SpacePalApplication.getInstance()).tokenObj

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(AuthorizationInterceptor(tokenResponse))
                .authenticator(SpacePalAuthenticator(tokenResponse))

        val builder = GsonBuilder().setExclusionStrategies(AnnotationExclusionStrategy()).create()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(builder))
                .client(okHttpClientBuilder.build())
                .build()

        api = retrofit.create(API::class.java!!)
    }


    inner class AuthorizationInterceptor(internal var tokenObj: TokenResponse) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            // header will be access_token
            val header = Util.getAuthorizationHeader(tokenObj)
            if (header != null) {
                val request = original.newBuilder()
                        .header("Authorization", "Bearer " + header!!).build()


                Log.d(TAG, "intercept: add header " + header!!)
                val response = chain.proceed(request)
                if (response.code() == 401) {
                    EventBus.getDefault().post(LoginFailEvent())
                }

                return response
            } else {
                Log.d(TAG, "intercept: add no header")
                val response = chain.proceed(original)
                return response
            }

        }
    }

    inner class SpacePalAuthenticator(private val tokenObj: TokenResponse) : Authenticator {
        private val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        internal var gson = Gson()

        @Throws(IOException::class)
        override fun authenticate(route: Route, response: Response): Request? {
            var request: Request? = null
            try {
                val refreshTokenRequest = Request.Builder()
                        .url("$TOKEN_URL/connect/token") // uncomment: either place base_url in string as here or make your own url
                        .post(FormBody.Builder()
                                .add("grant_type", "refresh_token")
                                .add("refresh_token", tokenObj.refreshToken) // uncomment: add refreshtoken here
                                .add("client_id", "spacePAL.admin")
                                .add("client_secret", "8cef9b1d-973b-4d70-bd73-623e4d5782b2")
                                .build())
                        .build()
                val result = client.newCall(refreshTokenRequest).execute()
                val resultBody = result.body()

                if (result.isSuccessful && resultBody != null) {
                    val authResult = gson.fromJson<Any>(resultBody.string(), TokenResponse::class.java)
                    PreferenceUtil.getInstance(SpacePalApplication.getInstance()).saveTokenObject(authResult as TokenResponse)


                    request = response.request().newBuilder()
                            .removeHeader("Authorization")
                            .addHeader("Authorization", authResult.tokenType + " " + authResult.accessToken)
                            .build()


                }

            } catch (e: Exception) {
                //            Timber.w(e) { "Failed to refresh auth token" }
                Log.e("something bad happened", e.toString())
            }

            if (request == null) {
                //While all credentials as we no longer have a valid user
                PreferenceUtil.getInstance(SpacePalApplication.getInstance()).tokenObj.accessToken = ""
                PreferenceUtil.getInstance(SpacePalApplication.getInstance()).tokenObj.refreshToken = ""
            }

            return request
        }
    }

    companion object {

        val BASE_URL = "https://api-spa.stage.roadhouse.com.au"
        val TOKEN_URL = "https://identity-spa.stage.roadhouse.com.au"

        var instance: RetrofitHelper? = null
            get() {
                return RetrofitHelper(BASE_URL)
            }

        private val TAG = "RetrofitHelper"

        var instanceForToken: RetrofitHelper? = null
            get() {
                return RetrofitHelper(TOKEN_URL)
            }
    }
}
