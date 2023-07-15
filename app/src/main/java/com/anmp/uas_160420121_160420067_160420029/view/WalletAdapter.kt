package com.anmp.uas_160420121_160420067_160420029.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.util.loadImage
import kotlinx.android.synthetic.main.wallet_list_item.view.*

class WalletAdapter(val walletList:ArrayList<Wallet>)
    : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>()  {
    class WalletViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wallet_list_item, parent, false)
        return WalletAdapter.WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.view.txtNamaWallet.text = walletList[position].namaWallet
        holder.view.txtSaldoWallet.text = walletList[position].saldoWallet
        holder.view.imageViewWallet.loadImage(walletList[position].photo_url,holder.view.progressBarWallet)
    }

    override fun getItemCount(): Int {return walletList.size}

    fun updateWalletList(newWalletList: ArrayList<Wallet>) {
        walletList.clear()
        walletList.addAll(newWalletList)
        notifyDataSetChanged()
    }
}