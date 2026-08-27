package com.davifiszbejn557716.lactareconnect.ui.screens.doar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.data.model.DiaAgenda
import com.davifiszbejn557716.lactareconnect.data.model.ExamePreDoacao
import com.davifiszbejn557716.lactareconnect.data.model.HorarioAgenda
import com.davifiszbejn557716.lactareconnect.data.model.StatusExame
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcButtonVariant
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.components.LcHeader
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentRed
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentTeal
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Line
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(onVoltar: () -> Unit, onConfirmado: () -> Unit) {
    val exames = remember { mutableStateListOf(*MockData.exames.toTypedArray()) }
    val enviados = exames.count { it.status == StatusExame.ENVIADO }
    val todosEnviados = enviados == exames.size

    var diaSelecionado by remember { mutableStateOf<DiaAgenda?>(null) }
    var horarioSelecionado by remember { mutableStateOf<HorarioAgenda?>(null) }
    var menuDataAberto by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val prontoParaConfirmar = todosEnviados && diaSelecionado != null && horarioSelecionado != null

    Scaffold(
        topBar = { LcHeader(title = "Agendar coleta", onBack = onVoltar) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            Surface(color = White, shadowElevation = 4.dp) {
                Box(modifier = Modifier.padding(16.dp)) {
                    LcButton(
                        text = if (prontoParaConfirmar) {
                            "Confirmar doação — ${diaSelecionado!!.label}, ${diaSelecionado!!.diaMes} · ${horarioSelecionado!!.label}"
                        } else {
                            "Envie os exames pendentes pra continuar"
                        },
                        enabled = prontoParaConfirmar,
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Doação agendada com sucesso! Obrigado por doar.")
                                onConfirmado()
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Exames pré-doação", style = MaterialTheme.typography.titleLarge, color = Ink)
                        Text("$enviados de ${exames.size} enviados", style = MaterialTheme.typography.bodySmall, color = Muted)
                    }
                    LcCard(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Por que pedimos? Os exames protegem você e os bebês que vão receber sua doação. São pedidos só uma vez.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Muted
                        )
                    }
                }
            }
            items(exames, key = { it.id }) { exame ->
                ExameItem(
                    exame = exame,
                    onEnviar = {
                        val index = exames.indexOfFirst { it.id == exame.id }
                        if (index >= 0) exames[index] = exame.copy(status = StatusExame.ENVIADO)
                    }
                )
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Escolha um dia para a coleta", style = MaterialTheme.typography.titleMedium, color = Ink)
                    Box {
                        LcButton(
                            text = diaSelecionado?.let { "${it.label}, ${it.diaMes}" } ?: "📅  Selecionar data",
                            variant = LcButtonVariant.Secondary,
                            onClick = { menuDataAberto = true }
                        )
                        DropdownMenu(expanded = menuDataAberto, onDismissRequest = { menuDataAberto = false }) {
                            MockData.diasAgenda.forEach { dia ->
                                DropdownMenuItem(
                                    text = { Text("${dia.label}, ${dia.diaMes}") },
                                    onClick = {
                                        diaSelecionado = dia
                                        menuDataAberto = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Horário disponível em casa", style = MaterialTheme.typography.titleMedium, color = Ink)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(MockData.horariosAgenda) { horario ->
                            HorarioChip(
                                horario = horario,
                                selecionado = horario == horarioSelecionado,
                                onClick = { horarioSelecionado = horario }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExameItem(exame: ExamePreDoacao, onEnviar: () -> Unit) {
    val enviado = exame.status == StatusExame.ENVIADO
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(if (enviado) AccentTeal else AccentRed, CircleShape)
        )
        Text(exame.nome, style = MaterialTheme.typography.bodyLarge, color = Ink, modifier = Modifier.weight(1f))
        if (enviado) {
            Icon(Icons.Filled.Check, contentDescription = "Enviado", tint = AccentTeal)
        } else {
            LcButton(
                text = "Enviar",
                onClick = onEnviar,
                variant = LcButtonVariant.Secondary,
                fullWidth = false
            )
        }
    }
}

@Composable
private fun HorarioChip(horario: HorarioAgenda, selecionado: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = if (selecionado) Brand else White,
        border = BorderStroke(1.dp, if (selecionado) Brand else Line)
    ) {
        Text(
            horario.label,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selecionado) White else Ink,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}
