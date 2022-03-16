package br.com.fiap.imc.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.imc.R
import br.com.fiap.imc.databinding.ActivityCadastroBinding
import br.com.fiap.imc.databinding.ActivityLoginBinding
import br.com.fiap.imc.model.User
import br.com.fiap.imc.retrofit.retrofitUserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuring the binding
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        binding.buttonGravar.setOnClickListener {
            gravarUsuario()
        }

    }

    private fun gravarUsuario() {
        // Criar o usuário
        val user = User(
            0,
            binding.editTextNome.text.toString(),
            binding.editTextEmail.text.toString(),
            binding.editTextSenha.text.toString(),
            binding.editTextPeso.text.toString().toInt(),
            binding.editTextAltura.text.toString().toDouble())


        // Fazer a requisição para o webservice (POST)
        val call = retrofitUserService().saveUser(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                Toast.makeText(
                    this@CadastroActivity,
                    "Usuário cadastrado com sucesso!!",
                    Toast.LENGTH_SHORT).show()

                finish()

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(
                    this@CadastroActivity,
                    "Ocorreu um erro!!",
                    Toast.LENGTH_SHORT).show()
            }

        })

    }

}