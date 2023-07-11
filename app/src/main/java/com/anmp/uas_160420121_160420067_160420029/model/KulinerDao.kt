package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface KulinerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg kuliner:Kuliner)

    @Query("SELECT * FROM kuliner")
    fun selectAllKuliner(): List<Kuliner>

    @Query("SELECT * FROM kuliner WHERE kid= :id")
    fun selectKuliner(id:Int): Kuliner

    @Delete
    fun deleteKuliner(kuliner:Kuliner)
}