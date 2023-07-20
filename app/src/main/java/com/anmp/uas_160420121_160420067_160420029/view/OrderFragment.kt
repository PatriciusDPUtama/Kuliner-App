package com.anmp.uas_160420121_160420067_160420029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderBinding
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
    private val orderListAdapter = OrderListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderKulinerViewModel::class.java)
//        viewModel.refresh()

        var recViewOK = view.findViewById<RecyclerView>(R.id.recViewOrder)
        recViewOK.layoutManager = LinearLayoutManager(context)
        recViewOK.adapter = orderListAdapter

        observeViewModel()
    }

    fun observeViewModel(){

    }

}