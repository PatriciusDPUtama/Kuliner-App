package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.User
import com.anmp.uas_160420121_160420067_160420029.util.buildKulinerDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListKulinerViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val kulinerLD = MutableLiveData<List<Kuliner>>()
    val kulinerLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingLD.postValue(true)
        kulinerLoadErrorLD.postValue( false)

        launch {
            val db = buildKulinerDb(getApplication())
            kulinerLD.postValue(db.kulinerDao().selectAllKuliner())
        }
    }

    fun loadInitial(){
        var arrayKuliner = ArrayList<Kuliner>()
        arrayKuliner.add(Kuliner("Ayam Geprek Mozarella","Chicken","Dada ayam goreng dengan keju mozarella","https://img-global.cpcdn.com/recipes/1e8c0ba976dead8b/680x482cq70/ayam-geprek-mozzarella-foto-resep-utama.webp",16000,20))
        arrayKuliner.add(Kuliner("Carbonara","Pasta","Spaghetti Carbonara with mushrooms and smoked beef","https://assets.bonappetit.com/photos/5a6f48f94f860a026c60fd71/1:1/w_1920,c_limit/pasta-carbonara.jpg",25000,20))
        arrayKuliner.add(Kuliner("California Roll","Sushi","Best California Roll From Japan","https://www.simplyrecipes.com/thmb/EgyBYE---ytAEssbF2zQP5eyDds=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Simply-Recipes-California-Roll-LEAD-05-3c3a2fb4a9034e5c8cb34d6a24d9731e.jpg",30000,15))

        launch {
            val db = buildKulinerDb(getApplication())
            for (kuliner in arrayKuliner)
            {
                db.kulinerDao().insertAll(kuliner)
            }
            kulinerLD.postValue(db.kulinerDao().selectAllKuliner())
        }

    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}