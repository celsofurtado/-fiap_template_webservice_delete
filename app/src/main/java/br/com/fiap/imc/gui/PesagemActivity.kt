package br.com.fiap.imc.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.imc.databinding.ActivityPesagemBinding
import br.com.fiap.imc.model.History
import br.com.fiap.imc.retrofit.getAuthorization
import br.com.fiap.imc.retrofit.retrofitHistoryService
import br.com.fiap.imc.shared.getSharedUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class PesagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configuração binding
        binding = ActivityPesagemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editTextHistoryDate.setText(LocalDate.now().toString())

        supportActionBar!!.hide()

        binding.buttonHistoryGravar.setOnClickListener {
            saveHistory()
        }

    }

    private fun saveHistory() {

        val user = getSharedUser(this)

        val history = History(
            0,
            binding.editTextHistoryDate.text.toString(),
            binding.editTextHistoryPeso.text.toString().toInt(), user)

        val call = retrofitHistoryService().saveHistory(history, getAuthorization(user))

        call.enqueue(object : Callback<History> {
            override fun onResponse(call: Call<History>, response: Response<History>) {
                Toast.makeText(
                    this@PesagemActivity,
                    "Data saved successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<History>, t: Throwable) {
                Toast.makeText(this@PesagemActivity,
                    "An error ocurred. Try again!", Toast.LENGTH_SHORT).show()
            }

        })

    }

}