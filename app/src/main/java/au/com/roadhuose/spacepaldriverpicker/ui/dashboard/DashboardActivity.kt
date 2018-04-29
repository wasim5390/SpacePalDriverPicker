package au.com.roadhuose.spacepaldriverpicker.ui.dashboard

import android.os.Bundle
import au.com.roadhuose.spacepaldriverpicker.BaseActivity
import au.com.roadhuose.spacepaldriverpicker.R
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil
import com.roadhourse.spacepal.ui.dashboard.OrderListFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardActivity : BaseActivity() {
    override val id: Int
        get() = R.layout.activity_dashboard

    override fun created(savedInstanceState: Bundle?) {


        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        sliding_tabs!!.setupWithViewPager(viewPager)

        val adapter = DashboardFragmentPagerAdapter(this, supportFragmentManager, PreferenceUtil.getInstance(this).account)
        val user = PreferenceUtil.getInstance(this).account


        val orderListFragments = java.util.ArrayList<OrderListFragment>()
        for (i in 0 until user.getRoles()!!.size) {
            orderListFragments.add(OrderListFragment.getInstance(user.getRoles()!!.get(i)!!, user.getId()!!))
            OrderListPresenter(orderListFragments[i], PreferenceUtil.getInstance(this))
            adapter.addFragment(orderListFragments[i])
        }

        // Set the adapter onto the view pager
        viewPager!!.adapter = adapter

    }
}
