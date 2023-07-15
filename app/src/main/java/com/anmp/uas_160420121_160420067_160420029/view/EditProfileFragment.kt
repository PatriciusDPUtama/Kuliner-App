package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentEditProfileBinding
import com.anmp.uas_160420121_160420067_160420029.model.User
import com.anmp.uas_160420121_160420067_160420029.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment(),EditProfileLayoutInferface {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater, R.layout.fragment_edit_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.editListener = this

        val shared: SharedPreferences = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        var editor = shared.edit()
        var userID = shared.getInt(MainActivity.uId,0)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(shared.getInt(MainActivity.uId,0))
        observeViewModel()

//        buttonEditUser.setOnClickListener {
//            if (enterPassword.text.isNullOrEmpty() or enterUsername.text.isNullOrEmpty() or
//                enterName.text.isNullOrEmpty() or enterRePassword.text.isNullOrEmpty()
//            ) {
//                Toast.makeText(
//                    this.context,
//                    "Please fill in all the fields needed.",
//                    Toast.LENGTH_LONG
//                ).show()
//            } else {
//                if (enterPassword.text.contentEquals(enterRePassword.text)) {
//                    var username = enterUsername.text.toString()
//                    var password = enterPassword.text.toString()
//                    var nama = enterName.text.toString()
//
//                    viewModel.updateUser(username,password,nama,userID)
//
//                    Toast.makeText(
//                        this.context,
//                        "You have successfully change your account.",
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                    Navigation.findNavController(it).popBackStack()
//
//                } else {
//                    Toast.makeText(
//                        this.context,
//                        "The password you entered isn't the same. Please enter your password.",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
//            enterUsername.setText(it.username.toString())
//            enterName.setText(it.nama.toString())

//            val shared: SharedPreferences = this.requireActivity()
//                .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
//            var editor = shared.edit()
//            editor.putString(MainActivity.uName,it.nama)
//            editor.putString(MainActivity.uUsername,it.password)
        })
    }

    override fun onEditUser(v: View, obj: User) {
        if (obj.nama.isNullOrEmpty() or obj.username.isNullOrEmpty() or obj.password.isNullOrEmpty()) {
            Toast.makeText(
                v.context,
                "Please fill in all the fields needed.",
                Toast.LENGTH_LONG
            ).show()
        } else {
                viewModel.updateUser(obj.username.toString(),obj.password.toString(),obj.nama.toString(),obj.uid)

                val shared: SharedPreferences = this.requireActivity()
                .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
                var editor = shared.edit()
                editor.putString(MainActivity.uName,obj.nama)
                editor.putString(MainActivity.uUsername,obj.password)
                editor.apply()

                Toast.makeText(
                    v.context,
                    "You have successfully change your account.",
                    Toast.LENGTH_LONG
                ).show()
                Navigation.findNavController(v).popBackStack()
            }
        }
    }
