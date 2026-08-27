package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.runtime.mutableStateOf

// Estado compartilhado entre os 3 passos do wizard de cadastro — mockado, sem persistência real.
class CadastroFormState {
    var nomeCompleto = mutableStateOf("")
    var cpf = mutableStateOf("")
    var email = mutableStateOf("")
    var dataNascimento = mutableStateOf("")

    var telefone = mutableStateOf("")
    var cep = mutableStateOf("")
    var rua = mutableStateOf("")
    var numero = mutableStateOf("")
    var bairro = mutableStateOf("")
    var cidade = mutableStateOf("")
    var uf = mutableStateOf("")

    var senha = mutableStateOf("")
    var confirmarSenha = mutableStateOf("")
}
