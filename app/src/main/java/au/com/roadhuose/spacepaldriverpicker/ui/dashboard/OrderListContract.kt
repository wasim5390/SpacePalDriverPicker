package au.com.roadhuose.spacepaldriverpicker.ui.dashboard

import au.com.roadhuose.spacepaldriverpicker.BasePresenter
import au.com.roadhuose.spacepaldriverpicker.BaseView
import au.com.roadhuose.spacepaldriverpicker.model.response.Order


class OrderListContract {
    public interface View : BaseView<Presenter> {
        fun showOrders(mListOrder: List<Order>)

        fun showMessage(text: String, alert: Boolean)
        fun showProgressDialog(isInProgress: Boolean)

    }


    public interface Presenter : BasePresenter {
        fun getOrders(role: String)
    }
}
