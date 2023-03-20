package com.balder.inventariotienda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balder.inventariotienda.adapter.StockAdapter
import com.balder.inventariotienda.models.Stock
import com.balder.inventariotienda.services.APIService
import com.balder.inventariotienda.ui.activities.NewStockActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*import kotlin.math.log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult*/

class MainActivity : AppCompatActivity() {
    private lateinit var stockAdapter: StockAdapter
    private var stockList = mutableListOf<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScan = findViewById<FloatingActionButton>(R.id.fab_scan)
        btnScan.setOnClickListener {
            navigateToNew()
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        stockAdapter = StockAdapter(stockList) { }
        //val adapter = StockAdapter(stockList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = stockAdapter

        //loadStocks()

    }

    private fun loadStocks() {
        val apiService = APIServiceBuilder.buildService(APIService::class.java)
        val requestCall = apiService.getStockList()

        requestCall.enqueue(object : Callback<List<Stock>> {
            override fun onResponse(call: Call<List<Stock>>, response: Response<List<Stock>>) {
                if (response.isSuccessful) {
                    stockList.clear()
                    stockList.addAll(response.body()!!)
                    stockAdapter.notifyDataSetChanged()
                } else {
                   // showErrorToast()
                }
            }

            override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
               //showErrorToast()
            }
        })
    }


    private fun navigateToNew() {
        val intent = Intent(this, NewStockActivity::class.java)
        startActivity(intent)
    }
}