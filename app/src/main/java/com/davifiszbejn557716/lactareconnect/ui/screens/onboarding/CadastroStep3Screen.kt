package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcButtonVariant
import com.davifiszbejn557716.lactareconnect.ui.components.LcInput
import com.davifiszbejn557716.lactareconnect.ui.components.LcProgressHeader
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted

@Composable
fun CadastroStep3Screen(
    form: CadastroFormState,
    onFinalizar: () -> Unit,
    onVoltar: () -> Unit
) {
    var senha by form.senha
    var confirmarSenha by form.confirmarSenha

    Scaffold(
        topBar = {
            LcProgressHeader(title = "Cadastro", step = 3, totalSteps = 3, onBack = onVoltar)
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
                Text("Proteja sua conta", style = MaterialTheme.typography.headlineLarge, color = Ink)
                Text(
                    "Defina uma senha para acessar sua conta depois.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Muted
                )
            }

            LcInput(label = "Senha", value = senha, onValueChange = { senha = it }, placeholder = "Senha", isPassword = true)
            LcInput(label = "Confirmar senha", value = confirmarSenha, onValueChange = { confirmarSenha = it }, placeholder = "Confirmar senha", isPassword = true)

            Column(modifier = Modifier.weight(1f)) {}

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                LcButton(
                    text = "Voltar",
                    onClick = onVoltar,
                    variant = LcButtonVariant.Secondary,
                    fullWidth = false,
                    modifier = Modifier.weight(1f)
                )
                LcButton(
                    text = "Finalizar cadastro",
                    onClick = onFinalizar,
                    fullWidth = false,
                    modifier = Modifier.weight(1.4f)
                )
            }
        }
    }
}
