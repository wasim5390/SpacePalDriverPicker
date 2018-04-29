package au.com.roadhuose.spacepaldriverpicker.ui.login

import au.com.roadhuose.spacepaldriverpicker.BasePresenter
import au.com.roadhuose.spacepaldriverpicker.BaseView
import au.com.roadhuose.spacepaldriverpicker.model.Role
import au.com.roadhuose.spacepaldriverpicker.model.User
import au.com.roadhuose.spacepaldriverpicker.model.response.TokenResponse

class LoginContract {

    interface View : BaseView<Presenter> {
        fun signInIsSuccessful(user: User)
        fun tokenRetrieved(response: TokenResponse)
        fun rolesRetrieved(roles: List<Role>)
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
