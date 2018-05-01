package com.spacepal.internal.app.ui.changepassword

import android.os.Bundle
import com.spacepal.internal.app.BaseActivity
import com.spacepal.internal.app.R
import kotlinx.android.synthetic.main.activity_changepassword.*


class ChangePasswordActivity : BaseActivity() {
    override val id: Int
        get() = R.layout.activity_changepassword

    override fun created(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val changePassFragment = ChangePasswordFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, changePassFragment)
        transaction.commit()
        ChangePasswordPresenter(changePassFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        this.finish()
        return true
    }

}