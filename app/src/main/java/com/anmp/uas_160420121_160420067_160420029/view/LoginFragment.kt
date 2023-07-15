package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
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
class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    // TODO: Rename and change types of parameters
    private var trueFalse = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.selectAll()

        buttonRegsiter.setOnClickListener {
            val action = LoginFragmentDirections.toRegister()
            Navigation.findNavController(it).navigate(action)
        }

        buttonLogin.setOnClickListener {
            var eUsername = view.findViewById<TextInputEditText>(R.id.loginUsername).text.toString()
            var ePassword = view.findViewById<TextInputEditText>(R.id.loginPassword).text.toString()
            viewModel.findUser(eUsername,ePassword)
            observeViewModel()

            if(trueFalse=="true")
            {
                val action = LoginFragmentDirections.backToProfile()
                Navigation.findNavController(it).navigate(action)
            }
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
                trueFalse = "true"

            }
            else{
                Toast.makeText(this.context,"The username or/and password you entered was wrong. Please try again.",Toast.LENGTH_SHORT).show()
            }
        })
    }




}