package com.anmp.uas_160420121_160420067_160420029.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.anmp.uas_160420121_160420067_160420029.R
import com.anmp.uas_160420121_160420067_160420029.util.loadImage
import com.anmp.uas_160420121_160420067_160420029.viewmodel.DetailKulinerViewModel
import kotlinx.android.synthetic.main.fragment_detail_kuliner.*

class DetailKulinerFragment : Fragment() {

    private lateinit var viewModel: DetailKulinerViewModel

    var idKuliner=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailKulinerViewModel::class.java)
        val uuid=DetailKulinerFragmentArgs.fromBundle(requireArguments()).kid
        viewModel.fetch(uuid)
        idKuliner=uuid
        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer{
            val txtNamaMenuDetail= view?.findViewById<TextView>(R.id.txtNamaMenuDetail)
            val txtCategoryMenuDetail = view?.findViewById<TextView>(R.id.txtCategoryMenuDetail)
            val txtHargaMenuDetail = view?.findViewById<TextView>(R.id.txtHargaMenuDetail)
            val txtDeskripsiMenuDetail=view?.findViewById<TextView>(R.id.txtDeskripsiMenuDetail)
            val btnOrder=view?.findViewById<Button>(R.id.btnOrder)
            val imageViewMenuDetail=view?.findViewById<ImageView>(R.id.imageViewMenuDetail)

            txtNamaMenuDetail?.setText(viewModel.kulinerLD.value?.name).toString()
            txtCategoryMenuDetail?.setText(viewModel.kulinerLD.value?.category).toString()
            txtHargaMenuDetail?.setText(viewModel.kulinerLD.value?.harga.toString())
            txtDeskripsiMenuDetail?.setText(viewModel.kulinerLD.value?.deskripsi).toString()

            imageViewMenuDetail?.loadImage(viewModel.kulinerLD.value?.photoUrl,progressBarMenuDetail)
            btnOrder?.setOnClickListener {
//                val action = DetailKulinerFragmentDirections.actionOrder(idKuliner)
//                Navigation.findNavController(it).navigate(action)
//                Log.d("showvoley", idKuliner.toString())
            }

        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_kuliner, container, false)
    }

}