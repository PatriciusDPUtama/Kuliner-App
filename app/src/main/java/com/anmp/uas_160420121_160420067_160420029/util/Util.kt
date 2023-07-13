package com.anmp.uas_160420121_160420067_160420029.util

import android.content.Context
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.model.UserDatabase


fun buildUserDb(context: Context):UserDatabase {
    val db = Room.databaseBuilder(context, UserDatabase::class.java, "newuserdb")
        .build()
    return db
}
