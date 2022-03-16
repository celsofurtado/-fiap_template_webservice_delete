package br.com.fiap.imc.shared

import android.content.Context
import br.com.fiap.imc.model.User

fun getSharedUser(context: Context): User {

    val userShared = context.getSharedPreferences("login", Context.MODE_PRIVATE)

    return User(
        userShared.getLong("userId", 0),
        userShared.getString("name", "")!!,
        userShared.getString("email", "")!!,
        userShared.getString("password", "")!!,
        userShared.getInt("weight", 0),
        userShared.getFloat("height", 0.0F).toDouble()
    )
}
