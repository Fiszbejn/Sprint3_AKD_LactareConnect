package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcInput
import com.davifiszbejn557716.lactareconnect.ui.components.LcProgressHeader
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted

@Composable
fun CadastroStep1Screen(
    form: CadastroFormState,
    onContinuar: () -> Unit,
    onVoltar: () -> Unit
) {
    var nomeCompleto by form.nomeCompleto
    var cpf by form.cpf
    var email by form.email
    var dataNascimento by form.dataNascimento

    Scaffold(
        topBar = {
            LcProgressHeader(title = "Cadastro", step = 1, totalSteps = 3, onBack = onVoltar)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Quem é você?", style = MaterialTheme.typography.headlineLarge, color = Ink)
                Text(
                    "Vamos começar pelo básico. Bem-vinda. ✨",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Muted
                )
            }

            LcInput(label = "Nome completo", value = nomeCompleto, onValueChange = { nomeCompleto = it }, placeholder = "Maria Teste Silva")
            LcInput(label = "CPF (documento oficial)", value = cpf, onValueChange = { cpf = it }, placeholder = "123.456.789-01", keyboardType = KeyboardType.Number)
            LcInput(label = "E-mail", value = email, onValueChange = { email = it }, placeholder = "maria.teste@example.com", keyboardType = KeyboardType.Email)
            LcInput(
                label = "Data de nascimento",
                value = dataNascimento,
                onValueChange = { dataNascimento = it },
                placeholder = "08/08/2001",
                hint = "Aceitamos pessoas doadoras a partir de 18 anos"
            )

            Column(modifier = Modifier.weight(1f)) {}

            LcButton(text = "Continuar →", onClick = onContinuar)
        }
    }
}
