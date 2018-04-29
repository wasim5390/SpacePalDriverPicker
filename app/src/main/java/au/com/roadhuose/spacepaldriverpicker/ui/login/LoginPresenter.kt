package au.com.roadhuose.spacepaldriverpicker.ui.login

import au.com.roadhuose.spacepaldriverpicker.Constant
import au.com.roadhuose.spacepaldriverpicker.Constant.*
import au.com.roadhuose.spacepaldriverpicker.model.Role
import au.com.roadhuose.spacepaldriverpicker.model.User
import au.com.roadhuose.spacepaldriverpicker.model.response.TokenResponse
import au.com.roadhuose.spacepaldriverpicker.source.RetrofitHelper
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil
import au.com.roadhuose.spacepaldriverpicker.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val loginView: LoginContract.View, private var preferenceUtil: PreferenceUtil) : LoginContract.Presenter, Constant {

    init {
        this.loginView.setPresenter(this)
    }

    override fun start() {
    }

    override fun signIn() {
//        loginView.showProgressDialog(true)

        val call = RetrofitHelper.instance!!.api.account
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                loginView.showProgressDialog(false)
                if (response.code() == 200) {
                    loginView.signInIsSuccessful(response.body()!!)
                    loginView.showProgressDialog(false)
                } else {

                    val error = Util.parseError(response)
                    loginView.showMessage(error.getErrorDescription()!!, true)

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                loginView.showProgressDialog(false)
                loginView.showMessage("Fail...", true)
                t.printStackTrace()
            }
        })


    }

    override fun getToken(username: String, password: String) {
        preferenceUtil.saveTokenObject(null)
        if (username == null || username.trim { it <= ' ' }.isEmpty() || !username.contains("@")) {
            loginView.showErrorOnEmail("Please enter a valid email", true)
            return
        } else {
            loginView.showErrorOnEmail("", false)
        }

        if (password == null || password.trim { it <= ' ' }.isEmpty()) {
            loginView.showErrorOnPassword("Please enter a password", true)
            return
        } else {
            loginView.showErrorOnPassword("", false)
        }

        loginView.showProgressDialog(true)

        val call = RetrofitHelper.instanceForToken!!.api.getToken(username, password, CLIENT_ID, CLIENT_SECRET, GRANT_TYPE)
        call.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
//                loginView.showProgressDialog(false)
                if (response.code() == 200) {
                    loginView.tokenRetrieved(response.body()!!)

                } else {
                    val error = Util.parseError(response)
                    when (error.getErrorCode()) {
                        "PASSWORD_INVALID" -> loginView.showErrorOnPassword("Invalid Password ${error.getRemTryAllowed()} tries remaining", true)
                        "USER_NOT_FOUND" -> loginView.showErrorOnEmail("Invalid email", true)
                        "USER_LOCKOUT" -> loginView.showMessage("Locked out!", true)
                        "USER_DISABLED" -> loginView.showMessage("Disabled!", true)
                    }
                }
                loginView.showProgressDialog(false)
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                loginView.showProgressDialog(false)
                loginView.showMessage("Fail...", true)
                t.printStackTrace()
            }
        })
    }

//    override fun getRoles() {
//
////        loginView.showProgressDialog(true)
//
//        val call = RetrofitHelper.instance!!.api.roles
//        call.enqueue(object : Callback<List<Role>> {
//            override fun onResponse(call: Call<List<Role>>, response: Response<List<Role>>) {
////                loginView.showProgressDialog(false)
//                if (response.code() == 200) {
//                    loginView.rolesRetrieved(response.body()!!)
//
//                } else {
//                    val error = Util.parseError(response)
//                    loginView.showMessage(error.getErrorDescription()!!, true)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Role>>, t: Throwable) {
//                loginView.showProgressDialog(false)
//                loginView.showMessage("Fail...", true)
//                t.printStackTrace()
//            }
//        })
//    }
}
