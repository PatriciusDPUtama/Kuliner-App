package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.util.buildKulinerDb
import com.anmp.uas_160420121_160420067_160420029.util.buildOrderDb
import com.anmp.uas_160420121_160420067_160420029.util.buildWalletDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WalletViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val walletLD = MutableLiveData<Wallet>()

    fun fetch(id : Int) {

        launch {
            val db = buildWalletDb(getApplication())
            walletLD.postValue(db.walletDao().selectWallet(id))
        }
    }

    fun createWallet(userid : Int){
        val wallet = Wallet(userid,"Wallet",1000000,"https://cdn-icons-png.flaticon.com/512/1796/1796828.png")
        launch {
            val db = buildWalletDb(getApplication())
            db.walletDao().insertAll(wallet)
            walletLD.postValue(db.walletDao().selectWallet(userid))
        }
    }

    fun update(id: Int, saldo: Int){
        launch {
            val db = buildWalletDb(getApplication())
            db.walletDao().updateWalletTopUp(id, saldo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}