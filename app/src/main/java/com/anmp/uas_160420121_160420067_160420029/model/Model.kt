package com.anmp.uas_160420121_160420067_160420029.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kuliner(
    @ColumnInfo(name="name")
    val name:String?,
    @ColumnInfo(name="category")
    val category:String?,
    @ColumnInfo(name="deskripsi")
    val deskripsi:String?,
    @ColumnInfo(name="photoUrl")
    val photoUrl:String?,
    @ColumnInfo(name="harga")
    val harga:Int?,
    @ColumnInfo(name="stok")
    val stok:Int?
){
    @PrimaryKey(autoGenerate = true)
    var kid:Int = 0
}

@Entity
data class Review(
    @ColumnInfo(name="idkuliner")
    val idKuliner:String?,
    @ColumnInfo(name="name")
    val name:String?,
    @ColumnInfo(name="review")
    val review:String?,
){
    @PrimaryKey(autoGenerate = true)
    var rid:Int = 0
}

@Entity
data class Orders(
    @ColumnInfo(name="kulinerorder")
    val kulinerOrder: String?,
    @ColumnInfo(name="tanggal")
    val tanggal:String?,
    @ColumnInfo(name = "quantity")
    val quantity : Int?,
    @ColumnInfo(name="photo_url")
    val photo_url:String?,
){
    @PrimaryKey(autoGenerate = true)
    var oid:Int = 0
}

@Entity
data class Promo(
    @ColumnInfo(name="namapromo")
    val namaPromo:String?,
    @ColumnInfo(name="kulinerpromo")
    val kulinerPromo:String?,
    @ColumnInfo(name="hargaasli")
    val hargaAsli:Int?,
    @ColumnInfo(name="hargadiskon")
    val hargaDiskon:Int?,
    @ColumnInfo(name="photo_url")
    val photo_url:String?,
)
{
    @PrimaryKey(autoGenerate = true)
    var pid:Int = 0
}

@Entity
data class Wallet(
    @ColumnInfo(name="iduser")
    val idUser:Int?,
    @ColumnInfo(name="namawallet")
    val namaWallet:String?,
    @ColumnInfo(name="saldowallet")
    val saldoWallet:String?,
    @ColumnInfo(name="photo_url")
    val photo_url:String?,
){
    @PrimaryKey(autoGenerate = true)
    var wid:Int = 0
}

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="nama")
    var nama:String?
){
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0
}