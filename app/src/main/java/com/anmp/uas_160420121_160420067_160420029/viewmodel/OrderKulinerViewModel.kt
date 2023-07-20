package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import kotlinx.coroutines.Job
import com.anmp.uas_160420121_160420067_160420029.model.Orders
import com.anmp.uas_160420121_160420067_160420029.model.User
import com.anmp.uas_160420121_160420067_160420029.util.buildKulinerDb
import com.anmp.uas_160420121_160420067_160420029.util.buildOrderDb
import com.anmp.uas_160420121_160420067_160420029.util.buildUserDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OrderKulinerViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val orderLD = MutableLiveData<Orders>()
    val orderListLD = MutableLiveData<List<Orders>>()
    val kulinerLD = MutableLiveData<Kuliner>()
    val userLD = MutableLiveData<User>()
    val loadingLD = MutableLiveData<Boolean>()
    val orderLoadErrorLD = MutableLiveData<Boolean>()

    fun addOrder(idUser: Int, namaPembeli: String, alamat: String, namaKuliner: String, tanggal: String, qty: Int, photoUrl: String, totalHarga: Int){
        val order = Orders(idUser, namaPembeli, alamat, namaKuliner, tanggal, qty, photoUrl, totalHarga)
        launch {
            val db = buildOrderDb(getApplication())
            db.orderDao().insertAll(order)
        }
    }

    fun selectOrder(oid: Int){
        launch {
            val db = buildOrderDb(getApplication())
            orderLD.postValue(db.orderDao().selectOrderById(oid))
        }
    }

    fun fetch(kid: Int) {
        launch {
            val db = buildKulinerDb(getApplication())
//            todoLD.value =  db.todoDao().selectTodo(uuid)
            kulinerLD.postValue(db.kulinerDao().selectKuliner(kid))
        }
    }

    fun refresh(id: Int){
        loadingLD.postValue(true)
        orderLoadErrorLD.postValue( false)

        launch {
            val db = buildOrderDb(getApplication())
            orderListLD.postValue(db.orderDao().selectOrderUser(id))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}