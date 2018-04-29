package com.roadhourse.spacepal.ui.login

import android.os.Bundle
import au.com.roadhuose.spacepaldriverpicker.BaseActivity
import au.com.roadhuose.spacepaldriverpicker.R
import au.com.roadhuose.spacepaldriverpicker.ui.login.LoginFragment
import au.com.roadhuose.spacepaldriverpicker.ui.login.LoginPresenter
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil

/**
 * Created by sidhu on 4/24/2018.
 */

public class LoginActivity : BaseActivity() {

    override fun created(savedInstanceState: Bundle?) {
        // setToolBar(toolbar,getText(R.string.login),true);
        val loginFragment = LoginFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, loginFragment)
        transaction.commit()
        val loginPresenter = LoginPresenter(loginFragment, PreferenceUtil.getInstance(this))
    }

    override val id: Int
        get() = R.layout.activity_login


}
