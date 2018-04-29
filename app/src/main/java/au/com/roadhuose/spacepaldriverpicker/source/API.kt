package au.com.roadhuose.spacepaldriverpicker.source

import au.com.roadhuose.spacepaldriverpicker.model.EmailBody
import au.com.roadhuose.spacepaldriverpicker.model.ForgotPassword
import au.com.roadhuose.spacepaldriverpicker.model.Role
import au.com.roadhuose.spacepaldriverpicker.model.User
import au.com.roadhuose.spacepaldriverpicker.model.response.Order
import au.com.roadhuose.spacepaldriverpicker.model.response.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface API {

    /*******************Roles */

    @get:GET("/v1/Role")
    val roles: Call<List<Role>>

    @get:GET("/v1/Users/Me")
    val account: Call<User>

    @POST("/connect/token")
    @FormUrlEncoded
    fun getToken(@Field("username") username: String, @Field("password") password: String,
                 @Field("client_id") clientId: String,
                 @Field("client_secret") clientSecret: String,
                 @Field("grant_type") grantType: String): Call<TokenResponse>


    @POST("/v1/User/forgot-password")
    fun forgotPass(@Body email: EmailBody): Call<Void>

    /** */

    /********************* Account  */
    @POST("/v1/Users")
    fun createAccount(@Body user: User): Call<User>

    @POST("/v1/Users")
    fun updateAccount(@Body user: User): Call<User>

    @GET("/v1/Order/Dash")
    fun getOrders(@Query("role") role: String): Call<List<Order>>


}

