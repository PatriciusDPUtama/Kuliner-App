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
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentDetailKulinerBinding
import com.anmp.uas_160420121_160420067_160420029.databinding.FragmentOrderKulinerBinding
import com.anmp.uas_160420121_160420067_160420029.util.NotificationHelper
import com.anmp.uas_160420121_160420067_160420029.viewmodel.OrderKulinerViewModel
import com.anmp.uas_160420121_160420067_160420029.viewmodel.WalletViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class OrderKulinerFragment : Fragment(), OrderLayoutInterface {
    private lateinit var viewModel:OrderKulinerViewModel
    private lateinit var viewModel2: WalletViewModel
    private lateinit var dataBinding: FragmentOrderKulinerBinding

    private lateinit var shared: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentOrderKulinerBinding>(inflater, R.layout.fragment_order_kuliner, container, false)
        return dataBinding.root
    }

    var idKuliner=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.orderlistener = this

        viewModel = ViewModelProvider(this).get(OrderKulinerViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(WalletViewModel::class.java)
        val kid=OrderKulinerFragmentArgs.fromBundle(requireArguments()).kid

        shared = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        val userid = shared.getInt(MainActivity.uId,0)

        viewModel.fetch(kid)
        viewModel2.fetch(userid)
        idKuliner=kid

        observeViewModel()
    }

    var namaKuliner=""
    var harga=0
    var photoUrl=""
    var totalHarga=0
    var saldo=0
    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer{
            dataBinding.kuliner = it
            namaKuliner = it.name.toString()
            harga = it.harga.toString().toInt()
            photoUrl = it.photoUrl.toString()
        })
        viewModel2.walletLD.observe(viewLifecycleOwner, Observer {
            saldo=it.saldoWallet.toString().toInt()
        })
    }

    override fun onButtonOrderClick(v: View) {
        shared = this.requireActivity()
            .getSharedPreferences(MainActivity.sharedFile, Context.MODE_PRIVATE)
        val userid = shared.getInt(MainActivity.uId,0)

        var quantity = dataBinding.qty?.toInt()
        var alamat = dataBinding.alamat?.toString()
        var namaPembeli = dataBinding.custName?.toString()

        var tanggalNow = Calendar.getInstance().time
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var tanggalOrder = formater.format(tanggalNow)

        totalHarga = harga * quantity.toString().toInt()
        var saldoAkhir = saldo-totalHarga

        if(saldo < totalHarga){
            Toast.makeText(v.context, "Saldo Anda Tidak Mencukupi. Silahkan Lakukan Top Up", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.addOrder(userid, namaPembeli.toString(), alamat.toString(), namaKuliner, tanggalOrder.toString(), quantity.toString().toInt(), photoUrl, totalHarga)
            viewModel2.update(userid, saldoAkhir)

            NotificationHelper(v.context)
                .createNotification("Order Notification",
                    "Pembelian Sebesar $totalHarga Berhasil")

            Toast.makeText(v.context, "Add Order Success", Toast.LENGTH_SHORT).show()
            val action = OrderKulinerFragmentDirections.backToHome()
            Navigation.findNavController(v).navigate(action)
        }
    }
}