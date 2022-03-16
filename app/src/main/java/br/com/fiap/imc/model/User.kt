package br.com.fiap.imc.model

data class User(
    var userId: Long = 0,
    var name: String,
    var email: String,
    var password: String,
    var weight: Int,
    var height: Double
)
