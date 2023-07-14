package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg orders: Orders)

    @Query("SELECT * FROM orders")
    fun selectAllOrder(): List<Orders>

    @Query("SELECT * FROM orders WHERE oid= :id")
    fun selectOrder(id:Int): Orders

    @Delete
    fun deleteOrder(orders:Orders)
}