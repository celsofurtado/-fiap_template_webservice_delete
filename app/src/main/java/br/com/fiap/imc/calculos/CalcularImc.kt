package br.com.fiap.imc.calculos

import kotlin.math.pow

fun fileCalcularImc(peso: Int, altura: Double) : Double {

    return peso / altura.pow(2)

}

fun definirStatusImc(imc: Double) : String {

    if (imc < 18.5) {
        return "Atenção, você está abaixo do peso!"
    } else if (imc >= 18.5 && imc < 25) {
        return "Parabéns, seu peso é o ideal!"
    } else if (imc >= 25 && imc < 30) {
        return "Cuidado, você está com sobrepeso."
    } else if (imc >= 30 && imc < 35) {
        return "Cuidado, obesidade Grau I."
    } else if (imc >= 35 && imc < 40) {
        return "Atenção, você está em Obesidade Grau II."
    } else {
        return "Atenção, você está em Obesidade Grau III"
    }

}