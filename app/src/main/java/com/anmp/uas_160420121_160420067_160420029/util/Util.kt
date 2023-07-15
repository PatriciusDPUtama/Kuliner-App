package com.anmp.uas_160420121_160420067_160420029.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?,progressBar: ProgressBar) {
    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object:Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })



}
fun buildUserDb(context: Context):UserDatabase {
    val db = Room.databaseBuilder(context, UserDatabase::class.java, "newuserdb")
        .build()
    return db
}

fun buildKulinerDb(context: Context):KulinerDatabase{
    val db = Room.databaseBuilder(context, KulinerDatabase::class.java, "newkulinerdb")
        .build()
    return db
}

fun buildPromoDb(context: Context):PromoDatabase{
    val db = Room.databaseBuilder(context, PromoDatabase::class.java, "newpromodb")
        .build()
    return db
}

fun buildOrderDb(context: Context):OrderDatabase{
    val db = Room.databaseBuilder(context, OrderDatabase::class.java, "neworderdb")
        .build()
    return db
}

fun buildWalletDb(context: Context):WalletDatabase{
    val db = Room.databaseBuilder(context, WalletDatabase::class.java, "newwalletdb")
        .build()
    return db
}
