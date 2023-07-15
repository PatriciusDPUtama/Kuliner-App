package com.anmp.uas_160420121_160420067_160420029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.Promo
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.util.loadImage
import kotlinx.android.synthetic.main.promo_list_item.view.*

class PromoAdapter(val promoList:ArrayList<Promo>)
    : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>()  {
    class PromoViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.promo_list_item, parent, false)
        return PromoAdapter.PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.view.txtMakananPromo.text = promoList[position].kulinerPromo
        holder.view.txtNamaPromo.text = promoList[position].namaPromo
        holder.view.txtHargaAsliPromo.text = promoList[position].hargaAsli.toString()
        holder.view.txtHargaAkhirPromo.text = promoList[position].hargaDiskon.toString()

        holder.view.imageViewPromo.loadImage(promoList[position].photo_url,holder.view.progressBarPromo)
    }

    override fun getItemCount(): Int {return promoList.size}

    fun updatePromoList(newWalletList: ArrayList<Promo>) {
        promoList.clear()
        promoList.addAll(newWalletList)
        notifyDataSetChanged()
    }
}