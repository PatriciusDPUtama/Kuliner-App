package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg wallet: Wallet)

    @Query("SELECT * FROM wallet")
    fun selectAllWallet(): List<Wallet>

    @Query("SELECT * FROM wallet WHERE wid= :id")
    fun selectWallet(id:Int): Wallet

    @Query("SELECT * FROM wallet WHERE iduser= :id")
    fun selectUserWallet(id:Int): List<Wallet>

    @Delete
    fun deleteWallet(wallet:Wallet)
}