package com.spacepal.internal.app.ui.login

import com.spacepal.internal.app.BasePresenter
import com.spacepal.internal.app.BaseView
import com.spacepal.internal.app.model.response.Profile
import com.spacepal.internal.app.model.response.TokenResponse

class LoginContract {

    interface View : BaseView<Presenter> {
        fun signInIsSuccessful(profile: Profile)
        fun tokenRetrieved(response: TokenResponse)
        fun userRetrieved(profile: Profile)
        fun showMessage(text: String, alert: Boolean)
        fun showErrorOnEmail(error: String, visible: Boolean)
        fun showErrorOnPassword(error: String, visible: Boolean)
        fun showProgressDialog(isInProgress: Boolean)
    }


    public interface Presenter : BasePresenter {
        fun signIn()
        fun getToken(username: String, password: String)
//        fun getRoles()
    }
}
