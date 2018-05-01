package com.spacepal.internal.app.ui.dashboard

import com.spacepal.internal.app.BasePresenter
import com.spacepal.internal.app.BaseView
import com.spacepal.internal.app.model.response.Order


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
