package br.com.fiap.imc.model

data class History(
    var historyId: Long = 0,
    var date: String,
    var weight: Int,
    var user: User
)
