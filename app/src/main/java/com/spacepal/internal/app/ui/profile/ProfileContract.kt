package com.spacepal.internal.app.ui.profile

import com.spacepal.internal.app.BasePresenter
import com.spacepal.internal.app.BaseView
import com.spacepal.internal.app.model.response.Profile

class ProfileContract {
    interface View : BaseView<Presenter> {
        fun submitSuccessfull(profile: Profile)
        fun showProgressDialog(isInProgress: Boolean)
        fun showMessage(text: String, alert: Boolean)
    }


    public interface Presenter : BasePresenter {
        fun submit(profile: Profile)
        fun getToken(username: String, password: String)
//        fun getRoles()
    }
}