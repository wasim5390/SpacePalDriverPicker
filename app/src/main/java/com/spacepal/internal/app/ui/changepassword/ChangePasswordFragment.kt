package com.spacepal.internal.app.ui.changepassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.roadhourse.spacepal.ui.login.LoginActivity
import com.spacepal.internal.app.BaseFragment
import com.spacepal.internal.app.R
import com.spacepal.internal.app.model.ChangePassword
import kotlinx.android.synthetic.main.fragment_changepassword.*

class ChangePasswordFragment : BaseFragment(), ChangePasswordContract.View {

    private lateinit var presenter: ChangePasswordContract.Presenter

    override val id: Int
        get() = R.layout.fragment_changepassword

    override fun initUI(view: View) {
        btnConfirm.setOnClickListener { changePassword() }
    }

    override fun changePasswordRequestSuccess() {
        showMessage("Password has been changed!", true)
        startActivity(Intent(mBaseActivity, LoginActivity::class.java))
    }

    override fun showMessage(text: String, alert: Boolean) {
        showAlert(text, alert)
    }

    override fun showProgressDialog(isInProgress: Boolean) {
        if (isInProgress)
            showProgress()
        else
            hideProgress()
    }

    override fun setPresenter(presenter: ChangePasswordContract.Presenter) {
        this.presenter = presenter
    }

    fun changePassword() {
        presenter.changePasswordRequest(ChangePassword(edOldPassword.text.toString(), edNewPassword.text.toString(), edConfirmNewPassword.text.toString()))
    }

    companion object {

        fun newInstance(): ChangePasswordFragment {
            val args = Bundle()
            val fragment = ChangePasswordFragment()
            fragment.arguments = args
            return fragment
        }

    }
}