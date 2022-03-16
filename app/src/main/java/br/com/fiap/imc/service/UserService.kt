package br.com.fiap.imc.service

import br.com.fiap.imc.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @POST("users")
    fun saveUser(@Body user: User): Call<User>

    @GET("users/email/{email}")
    fun findUserByEmail(
        @Path("email") email: String,
        @Header("Authorization") authorization: String
    ): Call<User>

}