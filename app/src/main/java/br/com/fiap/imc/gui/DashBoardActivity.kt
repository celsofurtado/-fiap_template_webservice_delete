package br.com.fiap.imc.gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.imc.adapter.HistoryAdapter
import br.com.fiap.imc.databinding.ActivityDashBoardBinding
import br.com.fiap.imc.repository.HistoryRepository
import br.com.fiap.imc.shared.getSharedUser

class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        binding.fbNovaPesagem.setOnClickListener {
            val intent = Intent(this, PesagemActivity::class.java)
            startActivity(intent)
        }


        // Configuração da RecyclerView
        binding.rvHistorico.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        historyAdapter = HistoryAdapter(this)

        binding.rvHistorico.adapter = historyAdapter

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {

        val user = getSharedUser(this)
        binding.textViewDashName.text = user.name
        binding.textViewDashEmail.text = user.email

        var historyRepository = HistoryRepository()
        val historyList = historyRepository.getHistoriesByUser(user, historyAdapter)

        Log.i("xpto", "Histories: $historyList")

    }

}