package com.anmp.uas_160420121_160420067_160420029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import kotlinx.android.synthetic.main.kuliner_list_item.view.*

class KulinerListAdapter(val kulinerList:ArrayList<Kuliner>)
    : RecyclerView.Adapter<KulinerListAdapter.KulinerViewHolder>()  {
    class KulinerViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):KulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kuliner_list_item, parent, false)
        return KulinerViewHolder(view)
    }

    override fun onBindViewHolder(holder: KulinerViewHolder, position: Int) {

    }
    override fun getItemCount(): Int {return kulinerList.size}

    fun updateKulinerList(newKulinerList: ArrayList<Kuliner>) {
        kulinerList.clear()
        kulinerList.addAll(newKulinerList)
        notifyDataSetChanged()
    }

}