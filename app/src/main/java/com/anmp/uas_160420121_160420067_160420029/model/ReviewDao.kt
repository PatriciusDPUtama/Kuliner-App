package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg review: Review)

    @Query("SELECT * FROM review")
    fun selectAllReview(): List<Review>

    @Query("SELECT * FROM review WHERE rid= :id")
    fun selectReview(id:Int): Review

    @Delete
    fun deleteReview(review:Review)
}