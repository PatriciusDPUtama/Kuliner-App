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

    fun addOrder(namaPembeli: String, alamat: String, namaKuliner: String, tanggal: String, qty: Int, photoUrl: String, totalHarga: Int){
        val order = Orders(namaPembeli, alamat, namaKuliner, tanggal, qty, photoUrl, totalHarga)
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

    fun fetch(kid: Int, userId: Int) {
        launch {
            val db1 = buildKulinerDb(getApplication())
            val db2 = buildUserDb(getApplication())
//            todoLD.value =  db.todoDao().selectTodo(uuid)
            kulinerLD.postValue(db1.kulinerDao().selectKuliner(kid))
            userLD.postValue(db2.userDao().selectUser(userId))
        }
    }

    fun refresh(name: String){
        loadingLD.postValue(true)
        orderLoadErrorLD.postValue( false)

        launch {
            val db = buildOrderDb(getApplication())
            orderListLD.postValue(db.orderDao().selectOrderByName(name))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}