package br.com.fiap.imc.retrofit

import android.util.Base64
import br.com.fiap.imc.model.User
import br.com.fiap.imc.service.HistoryService
import br.com.fiap.imc.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8080/api/"

fun getRetrofitFactory() =
    Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun retrofitUserService(): UserService =
    getRetrofitFactory()
        .create(UserService::class.java)

fun retrofitHistoryService(): HistoryService =
    getRetrofitFactory()
        .create(HistoryService::class.java)

fun getAuthorization(user: User): String {
    val email = user.email
    val password = user.password
    val login = "$email:$password"

    val data = login.toByteArray()
    val base64 = Base64.encodeToString(data, Base64.NO_WRAP)

    return "Basic $base64".trim()
}