package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
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
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

            textUsername.text = shared.getString(MainActivity.uUsername,"Guest")
            textName.text = shared.getString(MainActivity.uName,"")
        }

        login.setOnClickListener{
            if(shared.getString(MainActivity.uUsername,"")!!.isNotEmpty())
            {
                editor.remove(MainActivity.uUsername)
                editor.remove(MainActivity.uName)
                editor.apply()

                val action = ProfileFragmentDirections.toLogin()
                Navigation.findNavController(it).navigate(action)
            }
            else{
                val action = ProfileFragmentDirections.toLogin()
                Navigation.findNavController(it).navigate(action)
            }
        }

        buttonEditProfile.setOnClickListener {
            val action = ProfileFragmentDirections.toEditProfile()
            Navigation.findNavController(it).navigate(action)
        }
    }

}