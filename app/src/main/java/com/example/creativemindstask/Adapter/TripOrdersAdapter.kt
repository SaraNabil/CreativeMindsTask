package com.example.creativemindstask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creativemindstask.Model.OrderTrip
import com.example.creativemindstask.R
import kotlinx.android.synthetic.main.tab_item.view.*

class TripOrdersAdapter(private val context: Context, private val orders: List<OrderTrip>,private val flag:Boolean) :
    RecyclerView.Adapter<TripOrdersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tab_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val order = orders[position]
        holder.setData(order)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(order: OrderTrip?) {
            itemView.orderNameTv.text = order!!.title
            itemView.orderTimeTv.text = order.tripTime
            itemView.genderTv.text = order.gender
            itemView.tripTypeTv.text = order.tripType
            itemView.numOfPeopleTv.text = order.numOfPeople
            if (order.mark) itemView.orangeMark.visibility =
                View.VISIBLE else itemView.orangeMark.visibility = View.GONE
            itemView.orderImageIv.setBackgroundResource(order.image)
            if (flag)itemView.cancelBtn.visibility=View.VISIBLE
        }
    }
}