package com.anmp.uas_160420121_160420067_160420029.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Wallet::class), version =  1)
abstract class WalletDatabase:RoomDatabase() {
    abstract fun walletDao(): WalletDao

    companion object {
        @Volatile private var instance: WalletDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WalletDatabase::class.java,
                "newwalletdb").build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}