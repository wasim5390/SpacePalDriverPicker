package com.spacepal.internal.app.ui.forgotpassword

import com.spacepal.internal.app.BasePresenter
import com.spacepal.internal.app.BaseView

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