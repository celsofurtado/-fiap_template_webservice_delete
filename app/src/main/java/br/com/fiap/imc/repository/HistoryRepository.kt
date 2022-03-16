package br.com.fiap.imc.repository

import android.content.Context
import android.util.Base64
import android.util.Log
import android.widget.Toast
import br.com.fiap.imc.adapter.HistoryAdapter
import br.com.fiap.imc.model.History
import br.com.fiap.imc.model.User
import br.com.fiap.imc.retrofit.getAuthorization
import br.com.fiap.imc.retrofit.retrofitHistoryService
import br.com.fiap.imc.shared.getSharedUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryRepository {

    fun getHistoriesByUser(user: User, historyAdapter: HistoryAdapter): List<History> {

        var histories: ArrayList<History> = arrayListOf()

        var call = retrofitHistoryService()
            .getAllHistoriesByUserId(user.userId, getAuthorization(user))

        call.enqueue(object : Callback<ArrayList<History>> {
            override fun onResponse(call: Call<ArrayList<History>>, response: Response<ArrayList<History>>) {
                if (response.body() != null) {
                    histories = response.body()!!
                    historyAdapter.updateHistoryList(histories)
                }
                Log.i("xpto", "Call $histories")
            }

            override fun onFailure(call: Call<ArrayList<History>>, t: Throwable) {
                Log.i("xpto", "Falha ${t.message.toString()}")
            }

        })

        return histories
    }

    fun delete(historyId: Long, context: Context) {

        val user = getSharedUser(context)

        val call = retrofitHistoryService()
            .deleteHistory(historyId, getAuthorization(user))

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("xpto", response.code().toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("xpto", "An error occurred!")
            }


        })

    }

}