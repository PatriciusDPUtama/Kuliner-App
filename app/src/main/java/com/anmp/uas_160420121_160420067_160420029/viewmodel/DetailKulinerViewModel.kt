package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.KulinerDatabase
import com.anmp.uas_160420121_160420067_160420029.util.buildKulinerDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailKulinerViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    val kulinerLD = MutableLiveData<Kuliner>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(kid: Int) {
        launch {
            val db = buildKulinerDb(getApplication())
//            todoLD.value =  db.todoDao().selectTodo(uuid)
            kulinerLD.postValue(db.kulinerDao().selectKuliner(kid))
        }
    }
}