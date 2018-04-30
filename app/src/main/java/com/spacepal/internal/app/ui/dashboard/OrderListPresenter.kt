package com.spacepal.internal.app.ui.dashboard

import com.spacepal.internal.app.model.response.Order
import com.spacepal.internal.app.source.RetrofitHelper
import com.spacepal.internal.app.util.PreferenceUtil
import com.spacepal.internal.app.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderListPresenter(private val view: OrderListContract.View, private val preferenceUtil: PreferenceUtil) : OrderListContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {

    }

    override fun getOrders(role: String) {
//        view.showProgressDialog(true)

        val call = RetrofitHelper.instance!!.api!!.getOrders(role)
        call.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {

                if (response.code() == 200) {
                    view.showProgressDialog(false)
                    view.showOrders(response.body()!!)

                } else {
                    val error = Util.parseError(response)
                    view.showMessage(error.getErrorDescription()!!, true)

                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                view.showProgressDialog(false)
                view.showMessage("Fail...", true)
                t.printStackTrace()
            }
        })
    }
}
