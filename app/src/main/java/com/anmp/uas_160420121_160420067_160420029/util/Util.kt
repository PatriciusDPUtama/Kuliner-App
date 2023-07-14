package com.anmp.uas_160420121_160420067_160420029.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.KulinerDatabase
import com.anmp.uas_160420121_160420067_160420029.model.UserDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .into(this, object: Callback {
            override fun onSuccess() {

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
