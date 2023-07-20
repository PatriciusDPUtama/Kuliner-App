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

interface TopUpWalletLayoutInterface{
    fun onWalletTopUp(v:View)
}

interface TopUpLayoutInterface{
    fun onButtonTopUpClick(v: View)
}

interface OrderLayoutInterface{
    fun onButtonOrderClick(v: View)
}

interface DetailOrderLayoutInterface{
    fun onButtonDetaiilOrderClick(v: View)
}

interface RegisterLayoutInterface{
    fun onButtonRegisterClick(v:View)
}

interface LoginLayoutInterface{
    fun onButtonLoginClick(v:View)
    fun onButtonNoAcc(v:View)
}

interface ProfileLayoutInterface{
    fun onEditProfileClick(v:View)
    fun onLogoutLoginClick(v:View)
}