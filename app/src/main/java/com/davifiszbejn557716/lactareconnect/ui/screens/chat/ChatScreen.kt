package com.davifiszbejn557716.lactareconnect.ui.screens.chat

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.data.model.Mensagem
import com.davifiszbejn557716.lactareconnect.data.model.RemetenteMensagem
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandTint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var mensagemAtual by remember { mutableStateOf("") }
    val mensagens = remember { mutableStateListOf(*MockData.conversaInicial.toTypedArray()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Brand, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("L", color = White, style = MaterialTheme.typography.labelLarge)
                        }
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text("Lila", style = MaterialTheme.typography.titleMedium, color = Ink)
                            Text("Assistente virtual", style = MaterialTheme.typography.bodySmall, color = Muted)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = White)
            )
        },
        bottomBar = {
            Surface(color = White, shadowElevation = 4.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = mensagemAtual,
                        onValueChange = { mensagemAtual = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Escreva sua mensagem...") },
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true
                    )
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Brand, CircleShape)
                            .clickable {
                                if (mensagemAtual.isNotBlank()) {
                                    mensagens.add(Mensagem(RemetenteMensagem.USUARIO, mensagemAtual, "agora"))
                                    mensagemAtual = ""
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Enviar", tint = White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(mensagens) { mensagem -> Bubble(mensagem) }
        }
    }
}

@Composable
private fun Bubble(mensagem: Mensagem) {
    val isBot = mensagem.remetente == RemetenteMensagem.BOT
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isBot) Arrangement.Start else Arrangement.End
    ) {
        Column(modifier = Modifier.widthIn(max = 280.dp)) {
            Surface(
                color = if (isBot) BrandTint else Brand,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = mensagem.texto,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isBot) Ink else White,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }
    }
}
