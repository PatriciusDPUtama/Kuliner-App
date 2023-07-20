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
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderKulinerBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentTopUpBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.WalletListItemBinding
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.util.NotificationHelper
import com.anmp.uas_160420121_160420067_160420029.viewmodel.DetailKulinerViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.WalletViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_top_up.*
import kotlinx.android.synthetic.main.fragment_top_up.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopUpFragment : Fragment(), TopUpLayoutInterface {
    // TODO: Rename and change types of parameters

    private lateinit var viewModel: WalletViewModel
    private lateinit var dataBinding:FragmentTopUpBinding

    private lateinit var shared: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentTopUpBinding>(inflater, R.layout.fragment_top_up, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.topuplistener = this

        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        val id=TopUpFragmentArgs.fromBundle(requireArguments()).id

        viewModel.fetch(id)

        observeViewModel()
    }

    var saldo=0
    var perhitungan=0
//    var tambahan=0
//    var totalsaldo=""

    fun observeViewModel(){
        viewModel.walletLD.observe(viewLifecycleOwner, Observer {
            dataBinding.wallet = it
            saldo = it.saldoWallet.toString().toInt()
        })
    }

    override fun onButtonTopUpClick(v: View) {
        shared = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        val userid = shared.getInt(MainActivity.uId,0)

        var tambahan = dataBinding.saldoTambahan?.toInt()
        if(tambahan == null){
            tambahan = 0
            Toast.makeText(v.context, "Top Up Failed", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        } else {
            perhitungan = saldo+tambahan.toString().toInt()

            viewModel.update(userid,perhitungan)

            NotificationHelper(v.context)
            .createNotification("Top Up Notification",
                "Saldo Berhasil Tertambah Sebesar $tambahan")

            Toast.makeText(v.context, "Top Up Success", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

}