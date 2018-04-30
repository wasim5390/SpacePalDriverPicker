package com.spacepal.internal.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.spacepal.internal.app.BaseFragment
import com.spacepal.internal.app.Constant.ROLE_DRIVER
import com.spacepal.internal.app.Constant.ROLE_PICKER
import com.spacepal.internal.app.R
import com.spacepal.internal.app.model.Profile
import com.spacepal.internal.app.model.response.TokenResponse
import com.spacepal.internal.app.ui.dashboard.DashboardActivity
import com.spacepal.internal.app.ui.forgotpassword.ForgotPassActivity
import com.spacepal.internal.app.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LoginContract.View {
    override fun userRetrieved(profile: Profile) {
        PreferenceUtil.getInstance(this!!.activity!!).saveAccount(profile)
    }

    private lateinit var presenter: LoginContract.Presenter

    override val id: Int
        get() = R.layout.fragment_login


    override fun initUI(view: View) {
        btnLogin.setOnClickListener { onLoginClick() }
        textViewForgotPassword.setOnClickListener { onForgotPassClick() }
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun signInIsSuccessful(profile: Profile) {

        var accessible = false
        for (role in profile.roles!!) {
            if (role!!.toUpperCase() == ROLE_PICKER || role.toUpperCase() == ROLE_DRIVER) {
                accessible = true
                var profile = PreferenceUtil.getInstance(this.activity!!).account
                Log.e("address is ", profile.email)
                break
            }
        }
        if (accessible) {
            PreferenceUtil.getInstance(this!!.activity!!).saveAccount(profile)
            val intent = Intent(context, DashboardActivity::class.java)
            startActivity(intent)
        } else
            Toast.makeText(activity, "Don't have privilege to access this app", Toast.LENGTH_SHORT).show()

    }

    override fun tokenRetrieved(tokenResponse: TokenResponse) {
        PreferenceUtil.getInstance(this!!.activity!!).saveTokenObject(tokenResponse)
        presenter.signIn()
    }


    override fun showMessage(text: String, alert: Boolean) {
//        Toast.makeText(mBaseActivity, text, Toast.LENGTH_SHORT).show()
        alertMessages(text)
    }

    override fun showErrorOnEmail(error: String, visible: Boolean) {
        textInputLayoutEmail.isErrorEnabled = visible
        textInputLayoutEmail.error = error
    }

    override fun showErrorOnPassword(error: String, visible: Boolean) {
        textInputLayoutPassword.isErrorEnabled = visible
        textInputLayoutPassword.error = error
    }

    override fun showProgressDialog(isInProgress: Boolean) {
        if (isInProgress)
            showProgress()
        else
            hideProgress()

    }

    private fun onLoginClick() {
        presenter.getToken(editTextEmail.text.toString(), editTextPassword.text.toString())
    }

    private fun onForgotPassClick() {
        startActivity(Intent(mBaseActivity, ForgotPassActivity::class.java))
    }

    companion object {

        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

}