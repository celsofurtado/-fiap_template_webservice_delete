package br.com.fiap.imc.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import br.com.fiap.imc.R
import br.com.fiap.imc.databinding.ActivityLoginBinding
import br.com.fiap.imc.model.User
import br.com.fiap.imc.retrofit.retrofitUserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuring the binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textViewNovoUsuario.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        supportActionBar!!.hide()

        binding.btnLogin.setOnClickListener {
            autenticar()
        }

    }

    private fun autenticar() {
        val email = binding.editTextLoginEmail.text.toString()
        val password = binding.editTextLoginPasword.text.toString()

        val login = "$email:$password"
        val loginByteArray = login.toByteArray()
        val loginBase64 = Base64.encodeToString(loginByteArray, Base64.NO_WRAP)

        // Executar a chamada para o endpoint de login

        val call = retrofitUserService().findUserByEmail(email, "Basic $loginBase64".trim())

        call.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                Log.i("xpto", "Código HTTP: ${response.code().toString()}")

                Log.i("xpto", response.body().toString())

                if (response.code().toInt() == 401) {
                    Toast.makeText(
                        applicationContext,
                        "Invalid authentication!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (response.code().toInt() == 404) {
                    Toast.makeText(
                        applicationContext,
                        "User not found!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                val login = getSharedPreferences("login", MODE_PRIVATE)
                val edit = login.edit()

                edit.putLong("userId", response.body()!!.userId)
                edit.putString("name", response.body()!!.name)
                edit.putString("password", binding.editTextLoginPasword.text.toString())
                edit.putString("email", response.body()!!.email)
                edit.putInt("weight", response.body()!!.weight)
                edit.putFloat("height", response.body()!!.height.toFloat())
                edit.apply()

                val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
                startActivity(intent)
                this@LoginActivity.finish()

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("xpto", "Erro Login ${t.message.toString()}")
            }

        })

    }
}