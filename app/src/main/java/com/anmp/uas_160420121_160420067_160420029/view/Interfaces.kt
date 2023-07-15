package com.anmp.uas_160420121_160420067_160420029.view

import android.view.View
import com.anmp.uas_160420121_160420067_160420029.model.User

interface EditProfileLayoutInferface {
    fun onEditUser(v: View, obj:User)
}

interface KulinerListLayoutInterface{
    fun onDetailKuliner(v:View)
}

interface KulinerDetailLayoutInterface{
    fun onButtonOrderAddClick(v: View)
}