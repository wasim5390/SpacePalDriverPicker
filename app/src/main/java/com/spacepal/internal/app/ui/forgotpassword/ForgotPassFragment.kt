package com.spacepal.internal.app.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.roadhourse.spacepal.ui.login.LoginActivity
import com.spacepal.internal.app.BaseFragment
import com.spacepal.internal.app.R
import kotlinx.android.synthetic.main.fragment_forgotpassword.*

public class ForgotPassFragment : BaseFragment(), ForgotPassContract.View {
    override fun showProgressDialog(isInProgress: Boolean) {
        if (isInProgress)
            showProgress()
        else
            hideProgress()
    }

    private lateinit var presenter: ForgotPassContract.Presenter

    override fun showMessage(text: String, alert: Boolean) {
        showAlert(text, alert)
    }

    override fun setPresenter(presenter: ForgotPassContract.Presenter) {
        this.presenter = presenter
    }

    override fun forgotPassRequestSuccess() {
        showMessage("Forgot Password Request sent! Please check your inbox for further action.", true)
        startActivity(Intent(mBaseActivity, LoginActivity::class.java))
    }

    override val id: Int
        get() = R.layout.fragment_forgotpassword

    override fun initUI(view: View) {

        buttonSubmit.setOnClickListener { requestForgotPassword() }
    }

    fun requestForgotPassword() {
        presenter.forgotPassRequest(editTextEmail.text.toString())
    }

    companion object {

        fun newInstance(): ForgotPassFragment {
            val args = Bundle()
            val fragment = ForgotPassFragment()
            fragment.arguments = args
            return fragment
        }
    }


}