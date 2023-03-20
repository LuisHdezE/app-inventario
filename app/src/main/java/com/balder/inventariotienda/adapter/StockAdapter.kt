package com.balder.inventariotienda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.balder.inventariotienda.R
import com.balder.inventariotienda.models.Stock

class StockAdapter(private val stocks: MutableList<Stock>, private val onClickListener: (Stock) -> Unit) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = stocks[position]
        holder.bind(stock)
        holder.itemView.setOnClickListener { onClickListener(stock) }
    }

    override fun getItemCount(): Int = stocks.size

    fun updateStocks(newStocks: List<Stock>) {
        stocks.clear()
        stocks.addAll(newStocks)
        notifyDataSetChanged()
    }

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvStockName: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvStockCode: TextView = itemView.findViewById(R.id.tvCodigo)
        private val tvStockCount: TextView = itemView.findViewById(R.id.tvConteo)

        fun bind(stock: Stock) {
            tvStockName.text = stock.nombre
            tvStockCode.text = stock.codigo
            tvStockCount.text = stock.conteo.toString()
        }
    }
}
