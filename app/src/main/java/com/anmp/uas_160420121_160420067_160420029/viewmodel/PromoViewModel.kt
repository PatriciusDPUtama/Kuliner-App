package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Promo
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.util.buildKulinerDb
import com.anmp.uas_160420121_160420067_160420029.util.buildPromoDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PromoViewModel (application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val promoLD = MutableLiveData<List<Promo>>()

    fun refresh() {

        launch {
            val db = buildPromoDb(getApplication())
            promoLD.postValue(db.promoDao().selectAllPromo())

        }
    }

    fun loadInitial(){
        var arrayPromo = ArrayList<Promo>()
        arrayPromo.add(Promo("50% Discount for item with min. Rp. 100.000","Carbonara",100000,50000,"https://assets.bonappetit.com/photos/5a6f48f94f860a026c60fd71/1:1/w_1920,c_limit/pasta-carbonara.jpg"))

        launch {
            val db = buildPromoDb(getApplication())
            for (promo in arrayPromo)
            {
                db.promoDao().insertAll(promo)
            }
            promoLD.postValue(db.promoDao().selectAllPromo())
        }

    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}