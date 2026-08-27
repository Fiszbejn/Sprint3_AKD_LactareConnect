package com.davifiszbejn557716.lactareconnect.ui.screens.conta

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.ui.components.Avatar
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadge
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadgeTone
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentRed
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandLight
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandTint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@Composable
fun ContaScreen(onSair: () -> Unit) {
    val nutriz = MockData.nutrizAtual

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text("Minha conta", style = MaterialTheme.typography.headlineLarge, color = Ink)
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Avatar(
                        initials = nutriz.iniciais,
                        background = BrandLight,
                        contentColor = White
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(nutriz.nomeCompleto, style = MaterialTheme.typography.titleLarge, color = Ink)
                        Text(
                            "Doadora desde ${nutriz.dataCadastro}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Muted
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            LcBadge(text = nutriz.statusCadastro, tone = LcBadgeTone.Neutral)
                            LcBadge(text = "${MockData.saldoGotinhas} gotinhas", tone = LcBadgeTone.Brand)
                        }
                    }
                }
            }
            item {
                ContaSecao(
                    titulo = "Cadastro",
                    linhas = listOf(
                        ContaLinha(Icons.Filled.Person, "Dados pessoais", "${nutriz.nomeCompleto} · CPF ${nutriz.cpf}"),
                        ContaLinha(Icons.Filled.Home, "Contato e endereço", nutriz.telefone)
                    )
                )
            }
            item {
                ContaSecao(
                    titulo = "Preferências",
                    linhas = listOf(
                        ContaLinha(Icons.Filled.Notifications, "Notificações", "Ativadas"),
                        ContaLinha(Icons.Filled.Language, "Idioma", "Português (BR)")
                    )
                )
            }
            item {
                ContaSecao(
                    titulo = "Privacidade",
                    linhas = listOf(
                        ContaLinha(Icons.Filled.Lock, "Segurança e senha", null),
                        ContaLinha(Icons.AutoMirrored.Filled.Logout, "Sair da conta", null, danger = true, onClick = onSair)
                    )
                )
            }
        }
    }
}

private data class ContaLinha(
    val icon: ImageVector,
    val label: String,
    val value: String?,
    val danger: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
private fun ContaSecao(titulo: String, linhas: List<ContaLinha>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(titulo.uppercase(), style = MaterialTheme.typography.labelMedium, color = Muted)
        LcCard(modifier = Modifier.fillMaxWidth(), padding = PaddingValues(0.dp)) {
            Column {
                linhas.forEach { linha ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = linha.onClick)
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(BrandTint, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                linha.icon,
                                contentDescription = null,
                                tint = if (linha.danger) AccentRed else Brand,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                linha.label,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (linha.danger) AccentRed else Ink
                            )
                            if (linha.value != null) {
                                Text(linha.value, style = MaterialTheme.typography.bodySmall, color = Muted)
                            }
                        }
                    }
                }
            }
        }
    }
}
