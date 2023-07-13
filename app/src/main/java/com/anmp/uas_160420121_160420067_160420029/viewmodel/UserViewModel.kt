package com.anmp.uas_160420121_160420067_160420029.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.anmp.uas_160420121_160420067_160420029.model.User
import com.anmp.uas_160420121_160420067_160420029.model.UserDatabase
import com.anmp.uas_160420121_160420067_160420029.util.buildUserDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val userLD = MutableLiveData<User>()
    val loginLD = MutableLiveData<User>()

    fun addUser(user:User){
        launch {
            val db = buildUserDb(getApplication())
            db.userDao().insertAll(user)
        }
    }

    fun selectAll(){
        launch {
            val db = buildUserDb(getApplication())
            Log.d("All users",db.userDao().selectAllUser().toString())
        }
    }
    fun findUser(username:String,password:String){
        launch {
            val db = buildUserDb(getApplication())
            loginLD.postValue(db.userDao().getUser(username,password))
            Log.d("login",loginLD.value.toString())
        }
    }
    fun fetch(uuid:Int) {
        launch {
            val db = buildUserDb(getApplication())
            userLD.postValue(db.userDao().selectUser(uuid))
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}