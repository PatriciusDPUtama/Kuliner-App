package com.anmp.uas_160420121_160420067_160420029.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.model.Kuliner
import com.anmp.uas_160420121_160420067_160420029.model.Promo
import com.anmp.uas_160420121_160420067_160420029.viewmodel.ListKulinerViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.PromoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [PromoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PromoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: PromoViewModel
    private val promoAdapter = PromoAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PromoViewModel::class.java)
        viewModel.refresh()

        var recViewPromo = view.findViewById<RecyclerView>(R.id.recViewPromo)
        recViewPromo.layoutManager = LinearLayoutManager(context)
        recViewPromo.adapter = promoAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.promoLD.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty())
            {
                viewModel.loadInitial()
            }
            promoAdapter.updatePromoList(it as ArrayList<Promo>)
        })
    }
}