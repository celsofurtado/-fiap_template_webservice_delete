package br.com.fiap.imc.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.imc.R
import br.com.fiap.imc.calculos.definirStatusImc
import br.com.fiap.imc.calculos.fileCalcularImc
import br.com.fiap.imc.model.History

class HistoryAdapter(val context: Context) : RecyclerView.Adapter<HistoryAdapter.Holder>() {

    private var historyList: ArrayList<History> = arrayListOf()

    fun updateHistoryList(list: ArrayList<History>) {
        this.historyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.historico_recycler_view_layout, parent, false)

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val history = historyList[position]

        Log.i("xpto", history.toString())

        holder.textViewDataPesagem.text = history.date
        holder.textViewPeso.text = "${history.weight} Kg"

        val imc = fileCalcularImc(history.weight, history.user.height)
        
        holder.textViewImc.text = "${String.format("%.1f", imc)}"
        holder.textViewStatus.text = definirStatusImc(imc)

    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewDataPesagem: TextView = view.findViewById(R.id.rv_data_pesagem)
        val textViewPeso: TextView = view.findViewById(R.id.rv_peso)
        val textViewStatus: TextView = view.findViewById(R.id.rv_status)
        val textViewImc: TextView = view.findViewById(R.id.rv_imc)

    }

}