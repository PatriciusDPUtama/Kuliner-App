package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user:User)

    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uid= :id")
    fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUser(username:String,password:String):User

    @Delete
    fun deleteUser(user:User)
}