package au.com.roadhuose.spacepaldriverpicker.ui.forgotpassword

import android.os.Bundle
import au.com.roadhuose.spacepaldriverpicker.BaseActivity
import au.com.roadhuose.spacepaldriverpicker.R
import kotlinx.android.synthetic.main.activity_forgotpass.*


public class ForgotPassActivity : BaseActivity() {
    override val id: Int
        get() = R.layout.activity_forgotpass

    override fun created(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val forgotPassFragment = ForgotPassFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, forgotPassFragment)
        transaction.commit()
        ForgotPassPresenter(forgotPassFragment)
    }
}