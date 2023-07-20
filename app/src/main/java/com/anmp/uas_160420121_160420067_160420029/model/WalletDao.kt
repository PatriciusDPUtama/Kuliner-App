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

    @Query("UPDATE wallet SET saldowallet = :saldo WHERE iduser = :id")
    fun updateWalletTopUp(id:Int, saldo:Int)

    @Query("UPDATE wallet SET saldowallet=saldowallet-:saldo WHERE iduser=:id")
    fun updateWallet(id:Int, saldo:String)

    @Delete
    fun deleteWallet(wallet:Wallet)
}