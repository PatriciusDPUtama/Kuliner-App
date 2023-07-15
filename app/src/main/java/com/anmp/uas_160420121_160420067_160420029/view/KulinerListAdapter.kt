package com.anmp.uas_160420121_160420067_160420029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.KulinerListItemBinding
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.util.loadImage
import kotlinx.android.synthetic.main.kuliner_list_item.view.*

class KulinerListAdapter(val kulinerList:ArrayList<Kuliner>)
    : RecyclerView.Adapter<KulinerListAdapter.KulinerViewHolder>()  {
    class KulinerViewHolder(var view: KulinerListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):KulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<KulinerListItemBinding>(inflater, R.layout.kuliner_list_item, parent, false)
        return KulinerViewHolder(view)
    }

    override fun onBindViewHolder(holder: KulinerViewHolder, position: Int) {
        holder.view.kuliner = kulinerList[position]
//        holder.view.imageViewKuliner.loadImage(kulinerList[position].photoUrl)
        holder.view.btnDetailKuliner.setOnClickListener {
            val action = KulinerListFragmentDirections.actionDetailKuliner(kulinerList[position].kid)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {return kulinerList.size}

    fun updateKulinerList(newKulinerList: ArrayList<Kuliner>) {
        kulinerList.clear()
        kulinerList.addAll(newKulinerList)
        notifyDataSetChanged()
    }

}