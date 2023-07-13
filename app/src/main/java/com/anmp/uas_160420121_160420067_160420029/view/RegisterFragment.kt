package com.anmp.uas_160420121_160420067_160420029.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.User
import com.anmp.uas_160420121_160420067_160420029.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var viewModel:UserViewModel

    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        buttonRegisterUser.setOnClickListener {
            if (inputPassword.text.isNullOrEmpty() or inputUsername.text.isNullOrEmpty() or
                inputName.text.isNullOrEmpty() or inputRePassword.text.isNullOrEmpty()
            ) {
                Toast.makeText(
                    this.context,
                    "Please fill in all the fields needed.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (inputPassword.text.contentEquals(inputRePassword.text)) {
                    var name = inputName.text.toString()
                    var username = inputUsername.text.toString()
                    var password = inputPassword.text.toString()

                    var userInput = User(username, password, name)
                    Log.d("register", userInput.toString())
                    viewModel.addUser(userInput)

                    Toast.makeText(
                        this.context,
                        "You have successfully created an account. Please sign in.",
                        Toast.LENGTH_LONG
                    ).show()
                    val action = RegisterFragmentDirections.backToLogin()
                    Navigation.findNavController(it).navigate(action)

                } else {
                    Toast.makeText(
                        this.context,
                        "The password you entered isn't the same. Please enter your password.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}