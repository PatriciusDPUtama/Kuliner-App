package com.anmp.uas_160420121_160420067_160420029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.OrderListItemBinding
import com.anmp.uas_160420121_160420067_160420029.model.Orders

class OrderAdapter (val orderList:ArrayList<Orders>)
    : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(), DetailOrderLayoutInterface{
    class OrderViewHolder(var view: OrderListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<OrderListItemBinding>(inflater, R.layout.order_list_item, parent, false)
        return OrderAdapter.OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.view.order = orderList[position]
        holder.view.detaillistener = this
    }

    fun updateOrderList(newOrderList: ArrayList<Orders>) {
        orderList.clear()
        orderList.addAll(newOrderList)
        notifyDataSetChanged()
    }

    override fun onButtonDetaiilOrderClick(v: View) {
        var id = v.tag.toString().toInt()
        val action = OrderFragmentDirections.detailOrderKuliner(id)
        Navigation.findNavController(v).navigate(action)
    }
}