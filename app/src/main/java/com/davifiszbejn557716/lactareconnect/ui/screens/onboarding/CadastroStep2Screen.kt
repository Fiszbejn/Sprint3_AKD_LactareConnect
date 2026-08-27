package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.davifiszbejn557716.lactareconnect.ui.components.LcButtonVariant
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.components.LcInput
import com.davifiszbejn557716.lactareconnect.ui.components.LcProgressHeader
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentAmber
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted

@Composable
fun CadastroStep2Screen(
    form: CadastroFormState,
    onContinuar: () -> Unit,
    onVoltar: () -> Unit
) {
    var telefone by form.telefone
    var cep by form.cep
    var rua by form.rua
    var numero by form.numero
    var bairro by form.bairro
    var cidade by form.cidade
    var uf by form.uf

    Scaffold(
        topBar = {
            LcProgressHeader(title = "Cadastro", step = 2, totalSteps = 3, onBack = onVoltar)
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
                Text("Onde te encontramos?", style = MaterialTheme.typography.headlineLarge, color = Ink)
                Text(
                    "Vamos usar isso para combinar coletas perto de você.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Muted
                )
            }

            LcInput(label = "Telefone", value = telefone, onValueChange = { telefone = it }, placeholder = "11987654321", keyboardType = KeyboardType.Phone)
            LcInput(label = "CEP", value = cep, onValueChange = { cep = it }, placeholder = "01310100", keyboardType = KeyboardType.Number)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                LcInput(
                    label = "Rua",
                    value = rua,
                    onValueChange = { rua = it },
                    placeholder = "Avenida Paulista",
                    modifier = Modifier.weight(2f)
                )
                LcInput(
                    label = "Nº",
                    value = numero,
                    onValueChange = { numero = it },
                    placeholder = "1000",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                LcInput(
                    label = "Bairro",
                    value = bairro,
                    onValueChange = { bairro = it },
                    placeholder = "Bela Vista",
                    modifier = Modifier.weight(1.4f)
                )
                LcInput(
                    label = "Cidade",
                    value = cidade,
                    onValueChange = { cidade = it },
                    placeholder = "São Paulo",
                    modifier = Modifier.weight(1.4f)
                )
                LcInput(
                    label = "UF",
                    value = uf,
                    onValueChange = { uf = it },
                    placeholder = "SP",
                    modifier = Modifier.weight(0.8f)
                )
            }

            LcCard(borderColor = AccentAmber, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "🤍 Obrigado por dar o primeiro passo. Sua doação pode salvar vidas.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Ink
                )
            }

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
                    text = "Continuar →",
                    onClick = onContinuar,
                    fullWidth = false,
                    modifier = Modifier.weight(1.4f)
                )
            }
        }
    }
}
