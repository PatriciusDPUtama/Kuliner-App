package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentLoginBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentLoginBindingImpl
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderKulinerBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentRegisterBinding
import com.anmp.uas_160420121_160420067_160420029.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(),LoginLayoutInterface{
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding : FragmentLoginBinding
    // TODO: Rename and change types of parameters
//    private var trueFalse = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.loginlistener = this

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.selectAll()

        buttonRegsiter.setOnClickListener {

        }

        buttonLogin.setOnClickListener {


        }
    }

    fun observeViewModel() {
        viewModel.loginLD.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val shared: SharedPreferences = this.requireActivity()
                    .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
                var editor = shared.edit()

                editor.putString(MainActivity.uUsername,it.username)
                editor.putString(MainActivity.uName,it.nama)
                editor.putInt(MainActivity.uId,it.uid)
                editor.apply()

                Toast.makeText(this.context,"You have successfully login.",Toast.LENGTH_SHORT).show()

                val action = LoginFragmentDirections.loginToHome()
                this.findNavController().navigate(action)
            }
            else{
                Toast.makeText(this.context,"The username or/and password you entered was wrong. Please try again.",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onButtonLoginClick(v: View) {
        var eUsername = dataBinding.loginUsername.toString()
        var ePassword = dataBinding.loginPassword.toString()
        viewModel.findUser(eUsername,ePassword)
        observeViewModel()
    }

    override fun onButtonNoAcc(v: View) {
        val action = LoginFragmentDirections.toRegister()
        Navigation.findNavController(v).navigate(action)
    }

}