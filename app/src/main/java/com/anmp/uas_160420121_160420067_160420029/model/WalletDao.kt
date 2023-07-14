package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg wallet: Wallet)

    @Query("SELECT * FROM wallet")
    fun selectAllReview(): List<Wallet>

    @Query("SELECT * FROM wallet WHERE wid= :id")
    fun selectReview(id:Int): Wallet

    @Delete
    fun deleteReview(wallet:Wallet)
}