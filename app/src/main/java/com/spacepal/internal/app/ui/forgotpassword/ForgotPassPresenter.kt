package com.spacepal.internal.app.ui.forgotpassword

import android.util.Log
import com.spacepal.internal.app.model.EmailBody
import com.spacepal.internal.app.source.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class ForgotPassPresenter(private val forgotPassView: ForgotPassContract.View) : ForgotPassContract.Presenter {
    override fun forgotPassRequest(email: String) {
        forgotPassView.showProgressDialog(true)
        val call = RetrofitHelper.instance!!.api.forgotPass(EmailBody(email))
        call.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200) {
                    forgotPassView.forgotPassRequestSuccess()
                    forgotPassView.showProgressDialog(false)
                } else {
                    Log.e("response not 200", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("onFailure", t.message)
            }
        })
    }

    override fun start() {
    }

    init {
        forgotPassView.setPresenter(this)
    }

}