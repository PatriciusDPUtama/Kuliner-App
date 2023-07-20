package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentLoginBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentProfileBinding
import com.anmp.uas_160420121_160420067_160420029.model.Orders
import com.anmp.uas_160420121_160420067_160420029.viewmodel.OrderKulinerViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.PromoViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() ,ProfileLayoutInterface{
    // TODO: Rename and change types of parameters
    private lateinit var dataBinding : FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.profilelistener = this

        titleName.isVisible = false
        titleUsername.isVisible = false
        buttonEditProfile.isVisible = false

        var login = view.findViewById<FloatingActionButton>(R.id.fabLogin)

        val shared: SharedPreferences = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        var editor = shared.edit()

        Log.d("Shared",shared.getString(MainActivity.uUsername,"").toString())
        if(shared.getString(MainActivity.uUsername,"")!!.isNotEmpty())
        {
            titleName.isVisible = true
            titleUsername.isVisible = true
            buttonEditProfile.isVisible = true

            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.fetch(shared.getInt(MainActivity.uId,0))
            observeViewModel()

        }
        else{
            titleName.isVisible = true
            titleName.text = "Guest"
        }

        login.setOnClickListener{

        }

        buttonEditProfile.setOnClickListener {

        }
    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onEditProfileClick(v: View) {
        val action = ProfileFragmentDirections.toEditProfile()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onLogoutLoginClick(v: View) {
        val shared: SharedPreferences = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        var editor = shared.edit()
        if(shared.getString(MainActivity.uUsername,"")!!.isNotEmpty())
        {
            editor.remove(MainActivity.uUsername)
            editor.remove(MainActivity.uName)
            editor.remove(MainActivity.uId)
            editor.clear()
            editor.apply()

            val action = ProfileFragmentDirections.toLogin()
            Navigation.findNavController(v).navigate(action)
        }
        else{
            val action = ProfileFragmentDirections.toLogin()
            Navigation.findNavController(v).navigate(action)
        }
    }

}