package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Wallet
import com.anmp.uas_160420121_160420067_160420029.viewmodel.ListKulinerViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_wallet.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [WalletFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WalletFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: WalletViewModel
    private val walletAdapter = WalletAdapter(arrayListOf())

    private lateinit var shared: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        shared = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)

        txtErrorWallet.isVisible = true

        var recViewWallet = view.findViewById<RecyclerView>(R.id.recViewWallet)
        recViewWallet.layoutManager = LinearLayoutManager(context)
        recViewWallet.adapter = walletAdapter

        if(shared.getString(MainActivity.uUsername,"")!!.isNotEmpty())
        {
            txtErrorWallet.isVisible = false

            val userid = shared.getInt(MainActivity.uId,0)
            viewModel.fetch(userid)

            observeViewModel()
        }


    }

    fun observeViewModel() {
        viewModel.walletLD.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
            {
                viewModel.createWallet(shared.getInt(MainActivity.uId,0))
            }
            walletAdapter.updateWalletList(it as ArrayList<Wallet>)
        })
    }

}