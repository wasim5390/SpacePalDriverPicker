package com.roadhourse.spacepal.ui.dashboard

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.spacepal.internal.app.R


class DetailsAdapter(myDataSet: Array<String>) : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private val mDataset: Array<String> = myDataSet
    override fun getItemCount(): Int {
        return mDataset.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.mTextView.text = mDataset[position]
        holder.mCardView.setOnClickListener {
            val currentValue = mDataset[position]
            Log.d("CardView", "CardView Clicked: $currentValue")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val v = LayoutInflater.from(parent?.getContext())
                .inflate(R.layout.card_item, parent, false)
        return DetailsViewHolder(v)
    }

    class DetailsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mCardView: CardView = v.findViewById<View>(R.id.card_view) as CardView
        var mTextView: TextView = v.findViewById<View>(R.id.tv_text) as TextView
    }


}