package au.com.roadhuose.spacepaldriverpicker.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import au.com.roadhuose.spacepaldriverpicker.BaseFragment
import au.com.roadhuose.spacepaldriverpicker.Constant.ROLE_DRIVER
import au.com.roadhuose.spacepaldriverpicker.Constant.ROLE_PICKER
import au.com.roadhuose.spacepaldriverpicker.R
import au.com.roadhuose.spacepaldriverpicker.model.Role
import au.com.roadhuose.spacepaldriverpicker.model.User
import au.com.roadhuose.spacepaldriverpicker.model.response.TokenResponse
import au.com.roadhuose.spacepaldriverpicker.ui.dashboard.DashboardActivity
import au.com.roadhuose.spacepaldriverpicker.ui.forgotpassword.ForgotPassActivity
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LoginContract.View {
    override fun rolesRetrieved(roles: List<Role>) {

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

    override fun signInIsSuccessful(user: User) {

        var accessible = false
        for (role in user.getRoles()) {
            if (role.toUpperCase() == ROLE_PICKER || role.toUpperCase() == ROLE_DRIVER) {
                accessible = true
                break
            }
        }
        if (accessible) {
            PreferenceUtil.getInstance(this!!.activity!!).saveAccount(user)
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