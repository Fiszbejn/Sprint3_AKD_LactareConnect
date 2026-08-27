package com.davifiszbejn557716.lactareconnect.ui.screens.doar

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.data.model.BancoLeiteLactare
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadge
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadgeTone
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.Faint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Line
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoarMapScreen(onAgendar: () -> Unit) {
    var bancoSelecionadoId by remember { mutableStateOf(MockData.bancos.first().id) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Doar", style = MaterialTheme.typography.titleLarge, color = Ink) },
                actions = {
                    Icon(
                        Icons.Filled.History,
                        contentDescription = null,
                        tint = Faint,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = White)
            )
        },
        bottomBar = {
            Surface(color = White, shadowElevation = 4.dp) {
                Box(modifier = Modifier.padding(16.dp)) {
                    LcButton(text = "Agendar doação em casa", onClick = onAgendar)
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            MapaMock(modifier = Modifier.fillMaxWidth().height(220.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Bancos Lactare próximos", style = MaterialTheme.typography.titleMedium, color = Ink)
                        LcBadge(text = "COLETA EM CASA", tone = LcBadgeTone.Brand)
                    }
                    Text(
                        "Recolhemos sua doação onde for melhor pra você",
                        style = MaterialTheme.typography.bodySmall,
                        color = Muted,
                        modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                    )
                }
                items(MockData.bancos) { banco ->
                    BancoItem(
                        banco = banco,
                        selecionado = banco.id == bancoSelecionadoId,
                        onSelecionar = { bancoSelecionadoId = banco.id }
                    )
                }
            }
        }
    }
}

@Composable
private fun MapaMock(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color(0xFFEFEDE6))) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val streetColor = Color(0xFFDDD9CE)
            for (i in 1..4) {
                val x = size.width * i / 5f
                drawLine(streetColor, Offset(x, 0f), Offset(x, size.height), strokeWidth = 6f)
            }
            for (i in 1..3) {
                val y = size.height * i / 4f
                drawLine(streetColor, Offset(0f, y), Offset(size.width, y), strokeWidth = 6f)
            }
        }
        Icon(
            imageVector = Icons.Filled.Place,
            contentDescription = null,
            tint = Brand,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
private fun BancoItem(
    banco: BancoLeiteLactare,
    selecionado: Boolean,
    onSelecionar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(selected = selecionado, onClick = onSelecionar)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selecionado,
            onClick = onSelecionar,
            colors = RadioButtonDefaults.colors(selectedColor = Brand, unselectedColor = Line)
        )
        Column {
            Text(banco.nome, style = MaterialTheme.typography.bodyLarge, color = Ink)
            Text(banco.descricaoAtendimento, style = MaterialTheme.typography.bodySmall, color = Muted)
        }
    }
}
