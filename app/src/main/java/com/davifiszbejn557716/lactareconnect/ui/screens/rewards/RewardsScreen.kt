package com.davifiszbejn557716.lactareconnect.ui.screens.rewards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.data.model.CategoriaRecompensa
import com.davifiszbejn557716.lactareconnect.data.model.Recompensa
import com.davifiszbejn557716.lactareconnect.ui.components.Coin
import com.davifiszbejn557716.lactareconnect.ui.components.CoinSize
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcButtonVariant
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.components.LcChip
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandLight
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@Composable
fun RewardsScreen(onRecompensaSelecionada: (String) -> Unit) {
    var categoriaSelecionada by remember { mutableStateOf(CategoriaRecompensa.TODOS) }
    val recompensasFiltradas = MockData.recompensas.filter {
        categoriaSelecionada == CategoriaRecompensa.TODOS || it.categoria == categoriaSelecionada
    }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Recompensas", style = MaterialTheme.typography.headlineLarge, color = Ink)
                    Text(
                        "Troque suas gotinhas por cuidado pra você.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Muted
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(listOf(Brand, BrandLight)),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "SEU SALDO",
                            style = MaterialTheme.typography.labelMedium,
                            color = White.copy(alpha = 0.85f)
                        )
                        Coin(value = MockData.saldoGotinhas, dark = true, size = CoinSize.Lg)
                        Text(
                            "Meus resgates ›",
                            style = MaterialTheme.typography.labelLarge,
                            color = White
                        )
                    }
                }
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(CategoriaRecompensa.entries) { categoria ->
                        LcChip(
                            label = categoria.label,
                            selected = categoriaSelecionada == categoria,
                            onClick = { categoriaSelecionada = categoria }
                        )
                    }
                }
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(((recompensasFiltradas.size + 1) / 2 * 210).dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    gridItems(recompensasFiltradas) { recompensa ->
                        RewardCard(recompensa, onClick = { onRecompensaSelecionada(recompensa.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun RewardCard(recompensa: Recompensa, onClick: () -> Unit) {
    LcCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(corDe(recompensa.cor), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.CardGiftcard, contentDescription = null, tint = White)
            }
            Text(recompensa.nome, style = MaterialTheme.typography.bodyLarge, color = Ink, maxLines = 2)
            Text(recompensa.parceiro, style = MaterialTheme.typography.bodySmall, color = Muted)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Coin(value = recompensa.custoGotinhas, size = CoinSize.Sm)
                LcButton(
                    text = "Resgatar",
                    onClick = onClick,
                    variant = LcButtonVariant.Secondary,
                    fullWidth = false,
                    compact = true
                )
            }
        }
    }
}
