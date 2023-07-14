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
    val walletLD = MutableLiveData<List<Wallet>>()

    fun fetch(id : Int) {
        launch {
            val db = buildWalletDb(getApplication())
            walletLD.postValue(db.walletDao().selectUserWallet(id))
        }
    }

    fun createWallet(userid : Int){
        var arrayWallet = ArrayList<Wallet>()
        arrayWallet.add(Wallet(userid,"OVO","0","https://images.glints.com/unsafe/glints-dashboard.s3.amazonaws.com/company-logo/1fac829304066a942f3c5fd81434fae3.jpg"))
        arrayWallet.add(Wallet(userid,"Shopee Pay","0","https://blog.bangbeli.com/wp-content/uploads/2023/01/logo-sopipey.jpg"))
        launch {
            val db = buildWalletDb(getApplication())
            for(wallet in arrayWallet)
            {
                db.walletDao().insertAll(wallet)
            }
            walletLD.postValue(db.walletDao().selectUserWallet(userid))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}