package au.com.roadhuose.spacepaldriverpicker.model.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.roadhuose.spacepaldriverpicker.R
import au.com.roadhuose.spacepaldriverpicker.model.response.Order
import kotlinx.android.synthetic.main.card_item.view.*

class OrderAdapter(val context: Context?, val orderList: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        var mOrderList = orderList.get(position)
        holder.orderRefNumber.text = mOrderList.orderId
    }


    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderRefNumber = itemView.tv_text!!
    }

}