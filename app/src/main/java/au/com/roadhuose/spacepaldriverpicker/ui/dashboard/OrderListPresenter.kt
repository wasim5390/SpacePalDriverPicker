package au.com.roadhuose.spacepaldriverpicker.ui.dashboard

import au.com.roadhuose.spacepaldriverpicker.model.response.Order
import au.com.roadhuose.spacepaldriverpicker.source.RetrofitHelper
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil
import au.com.roadhuose.spacepaldriverpicker.util.Util
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
//                    view.showProgressDialog(false)
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
