package br.com.fiap.imc.service

import br.com.fiap.imc.model.History
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface HistoryService {

    @GET("histories/user/{userId}")
    fun getAllHistoriesByUserId(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String) : Call<ArrayList<History>>

    @POST("histories")
    fun saveHistory(
        @Body history: History,
        @Header("Authorization") authorization: String): Call<History>

    @DELETE("histories/{historyId}")
    fun deleteHistory(
        @Path("historyId") historyId: Long,
        @Header("Authorization") authorization: String) : Call<ResponseBody>

}