package com.davifiszbejn557716.lactareconnect.ui.screens.rewards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material3.Icon
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.ui.components.Coin
import com.davifiszbejn557716.lactareconnect.ui.components.CoinSize
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.components.LcHeader
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentAmber
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun RewardDetailScreen(recompensaId: String, onVoltar: () -> Unit, onResgatado: () -> Unit) {
    val recompensa = remember(recompensaId) {
        MockData.recompensas.first { it.id == recompensaId }
    }
    val nutriz = MockData.nutrizAtual
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { LcHeader(title = recompensa.nome, onBack = onVoltar) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(corDe(recompensa.cor), RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.CardGiftcard, contentDescription = null, tint = White)
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(recompensa.nome, style = MaterialTheme.typography.headlineLarge, color = Ink)
                    Text(recompensa.parceiro, style = MaterialTheme.typography.bodyMedium, color = Muted)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Coin(value = recompensa.custoGotinhas, size = CoinSize.Md)
                        Text("Você tem ${MockData.saldoGotinhas} gotinhas", style = MaterialTheme.typography.bodySmall, color = Muted)
                    }
                }
            }
            if (recompensa.descricaoItens.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("O que vem na cesta", style = MaterialTheme.typography.titleMedium, color = Ink)
                        recompensa.descricaoItens.forEach {
                            Text("•  $it", style = MaterialTheme.typography.bodyMedium, color = Muted)
                        }
                    }
                }
            }
            item {
                LcCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Endereço de entrega", style = MaterialTheme.typography.labelMedium, color = Muted)
                            Text(
                                "${nutriz.endereco.rua}, ${nutriz.endereco.numero} · ${nutriz.endereco.bairro}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Ink
                            )
                        }
                        Text("Trocar", style = MaterialTheme.typography.labelLarge, color = Brand)
                    }
                }
            }
            item {
                LcCard(modifier = Modifier.fillMaxWidth(), borderColor = AccentAmber) {
                    Text(
                        "Você é incrível. Cada gota importa — obrigado por doar.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Ink
                    )
                }
            }
            item {
                LcButton(
                    text = "Resgatar agora — ${recompensa.custoGotinhas} gotinhas",
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Resgate confirmado! Você receberá novidades em breve.")
                            onResgatado()
                        }
                    }
                )
            }
        }
    }
}
