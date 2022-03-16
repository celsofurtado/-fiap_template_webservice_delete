package br.com.fiap.imc.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.fiap.imc.R
import br.com.fiap.imc.calculos.fileCalcularImc
import br.com.fiap.imc.calculos.definirStatusImc

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val peso = intent.getIntExtra("peso", 0)
        val altura = intent.getDoubleExtra("altura", 0.0)

        //Calcular Imc
        val imc = fileCalcularImc(peso, altura)

        // Obter o status do IMC
        val statusIMC = definirStatusImc(imc)

        //Exibir os resultados
        findViewById<TextView>(R.id.text_view_resultado_imc).text = String.format("%.2f", imc)
        findViewById<TextView>(R.id.text_view_status_imc).text = statusIMC

        // Gravar os dados no SharedPreferences
        //gravarDadosShared(peso, altura)
        gravarDadosArquivo(peso, altura)
    }

    private fun gravarDadosArquivo(peso: Int, altura: Double) {
        val dados = "$peso:$altura"

        //Abrir o arquivo
        val fs = openFileOutput("dados_arquivo", MODE_PRIVATE)
        fs.write(dados.toByteArray())
        fs.close()

    }

    private fun gravarDadosShared(peso: Int, altura: Double) {
        // Obter uma instancia do arquivo SharedPreferenes
        var dados = getSharedPreferences("dados", MODE_PRIVATE)
        var editor = dados.edit()
        editor.putInt("peso", peso)
        editor.putFloat("altura", altura.toFloat())
        editor.apply()
    }
}