package com.spacepal.internal.app.ui.dashboard

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.spacepal.internal.app.model.Profile
import java.util.*

class DashboardFragmentPagerAdapter(private val mContext: Context, fm: FragmentManager, internal var profile: Profile) : FragmentStatePagerAdapter(fm) {
    private val fragments = ArrayList<Fragment>()

    // This determines the fragment for each tab
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    // This determines the number of tabs
    override fun getCount(): Int {
        return profile.roles!!.size
    }

    // This determines the title for each tab
    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return profile.roles!!.get(position)
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}
