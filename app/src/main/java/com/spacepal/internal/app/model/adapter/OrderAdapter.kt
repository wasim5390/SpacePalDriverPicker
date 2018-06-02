package com.spacepal.internal.app.model.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacepal.internal.app.Constant
import com.spacepal.internal.app.R
import com.spacepal.internal.app.model.response.AssignmentItem
import com.spacepal.internal.app.util.Util
import kotlinx.android.synthetic.main.item_view_order.view.*

class OrderAdapter(val context: Context?, val assignmentItemList: List<AssignmentItem>)
    : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(),Constant  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_order, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return assignmentItemList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        var mOrderList = assignmentItemList[position]
        holder.itemRefNumber.text = mOrderList.id
        holder.itemTitle.text = mOrderList.userFullName
        holder.itemStatus.text = mOrderList.assignmentType
        holder.itemPriority.text = Util.getPriority(mOrderList.priority)
        Util.setPriorityDrawable(mOrderList.priority,holder.itemStatus)

    }


    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemRefNumber = itemView.tvItemReference!!
        var itemTitle = itemView.tvItemTitle!!
        var itemStatus = itemView.tvItemStatus!!
        var itemPriority = itemView.tvPriority!!
    }

}