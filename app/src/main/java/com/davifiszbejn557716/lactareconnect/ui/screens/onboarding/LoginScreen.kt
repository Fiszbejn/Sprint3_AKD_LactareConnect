package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.components.LcBrandMark
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcInput
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted

@Composable
fun LoginScreen(
    onEntrar: () -> Unit,
    onCadastrar: () -> Unit,
    onVoltar: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                IconButton(onClick = onVoltar, modifier = Modifier.padding(end = 4.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Ink)
                }
                LcBrandMark()
            }

            Column(
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("Que bom te ver de novo", style = MaterialTheme.typography.headlineLarge, color = Ink)
                Text(
                    "Entre com seu e-mail e senha para continuar doando.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Muted
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                LcInput(
                    label = "E-mail",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "E-mail",
                    keyboardType = KeyboardType.Email
                )
                LcInput(
                    label = "Senha",
                    value = senha,
                    onValueChange = { senha = it },
                    placeholder = "Senha",
                    isPassword = true
                )
                LcButton(text = "Entrar", onClick = onEntrar)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Ainda não é uma pessoa doadora? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Muted
                )
                Text(
                    "Cadastre-se",
                    style = MaterialTheme.typography.labelLarge,
                    color = Brand,
                    modifier = Modifier.clickable(onClick = onCadastrar)
                )
            }
        }
    }
}
