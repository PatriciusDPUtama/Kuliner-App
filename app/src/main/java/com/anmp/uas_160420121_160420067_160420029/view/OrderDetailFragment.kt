package com.anmp.uas_160420121_160420067_160420029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderDetailBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentTopUpBinding
import com.anmp.uas_160420121_160420067_160420029.viewmodel.OrderKulinerViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: OrderKulinerViewModel
    private lateinit var dataBinding: FragmentOrderDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentOrderDetailBinding>(inflater, R.layout.fragment_order_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OrderKulinerViewModel::class.java)
        val id=OrderDetailFragmentArgs.fromBundle(requireArguments()).id

        viewModel.selectOrder(id)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.orderLD.observe(viewLifecycleOwner, Observer {
            dataBinding.order = it
        })
    }
}