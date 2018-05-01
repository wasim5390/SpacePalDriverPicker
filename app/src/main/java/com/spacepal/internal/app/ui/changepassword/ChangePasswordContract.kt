package com.spacepal.internal.app.ui.changepassword

import com.spacepal.internal.app.BasePresenter
import com.spacepal.internal.app.BaseView
import com.spacepal.internal.app.model.ChangePassword

class ChangePasswordContract {
    interface View : BaseView<Presenter> {
        fun changePasswordRequestSuccess()
        fun showMessage(text: String, alert: Boolean)
        fun showProgressDialog(isInProgress: Boolean)
    }


    public interface Presenter : BasePresenter {
        fun changePasswordRequest(changePassword: ChangePassword)
    }
}