package au.com.roadhuose.spacepaldriverpicker.ui.forgotpassword

import au.com.roadhuose.spacepaldriverpicker.BasePresenter
import au.com.roadhuose.spacepaldriverpicker.BaseView

class ForgotPassContract {

    interface View : BaseView<Presenter> {
        fun forgotPassRequestSuccess()
        fun showMessage(text: String, alert: Boolean)
        fun showProgressDialog(isInProgress: Boolean)
    }


    public interface Presenter : BasePresenter {
        fun forgotPassRequest(email: String)
    }

}