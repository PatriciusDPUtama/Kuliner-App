package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface PromoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg promo: Promo)

    @Query("SELECT * FROM promo")
    fun selectAllReview(): List<Promo>

    @Query("SELECT * FROM promo WHERE pid= :id")
    fun selectReview(id:Int): Promo

    @Delete
    fun deleteReview(promo: Promo)
}