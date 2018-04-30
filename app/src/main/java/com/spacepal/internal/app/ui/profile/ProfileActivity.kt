package com.spacepal.internal.app.ui.profile

import android.os.Bundle
import com.spacepal.internal.app.BaseActivity
import com.spacepal.internal.app.R
import com.spacepal.internal.app.util.PreferenceUtil
import kotlinx.android.synthetic.main.activity_forgotpass.*

public class ProfileActivity : BaseActivity() {
    override val id: Int
        get() = R.layout.activity_profile

    override fun created(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val profilefragment = ProfileFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, profilefragment)
        transaction.commit()
        ProfilePresenter(profilefragment, PreferenceUtil.getInstance(this))
    }

}