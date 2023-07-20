package com.anmp.uas_160420121_160420067_160420029.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentWalletBinding
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Orders
import com.anmp.uas_160420121_160420067_160420029.viewmodel.OrderKulinerViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: OrderKulinerViewModel
    private lateinit var dataBinding:FragmentOrderBinding
    private val orderListAdapter = OrderAdapter(arrayListOf())

    private lateinit var shared: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentOrderBinding>(inflater, R.layout.fragment_order, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        val userName = shared.getString(MainActivity.uName, "")

        if(shared.getString(MainActivity.uUsername,"")!!.isNotEmpty()){
            viewModel = ViewModelProvider(this).get(OrderKulinerViewModel::class.java)
            viewModel.refresh(userName.toString())

            var recViewOK = view.findViewById<RecyclerView>(R.id.recViewOrder)
            recViewOK.layoutManager = LinearLayoutManager(context)
            recViewOK.adapter = orderListAdapter

            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.orderListLD.observe(viewLifecycleOwner, Observer {
            orderListAdapter.updateOrderList(it as ArrayList<Orders>)
        })
    }

}