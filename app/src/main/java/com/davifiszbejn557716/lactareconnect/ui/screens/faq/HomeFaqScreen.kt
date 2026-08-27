package com.davifiszbejn557716.lactareconnect.ui.screens.faq

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.data.mock.MockData
import com.davifiszbejn557716.lactareconnect.data.model.CategoriaFaq
import com.davifiszbejn557716.lactareconnect.data.model.PerguntaFrequente
import com.davifiszbejn557716.lactareconnect.ui.components.LcCard
import com.davifiszbejn557716.lactareconnect.ui.components.LcChip
import com.davifiszbejn557716.lactareconnect.ui.components.LcInput
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandTint
import com.davifiszbejn557716.lactareconnect.ui.theme.Faint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@Composable
fun HomeFaqScreen() {
    var busca by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf<CategoriaFaq?>(null) }
    var perguntaAbertaId by remember { mutableStateOf<String?>(MockData.faqs[3].id) }

    val perguntasFiltradas = MockData.faqs.filter { faq ->
        (categoriaSelecionada == null || faq.categoria == categoriaSelecionada) &&
            (busca.isBlank() || faq.pergunta.contains(busca, ignoreCase = true))
    }

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Perguntas frequentes", style = MaterialTheme.typography.headlineLarge, color = Ink)
                    Text(
                        "Tudo o que você quer saber, com carinho.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Muted
                    )
                }
            }
            item {
                LcCard(borderColor = Brand, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Você não está sozinha com essa dúvida. Reunimos aqui as perguntas mais comuns sobre a doação de leite humano.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Ink
                    )
                }
            }
            item {
                LcInput(
                    label = "Buscar",
                    value = busca,
                    onValueChange = { busca = it },
                    placeholder = "Buscar uma pergunta..."
                )
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        LcChip(
                            label = "Todas",
                            selected = categoriaSelecionada == null,
                            onClick = { categoriaSelecionada = null },
                            count = MockData.faqs.size
                        )
                    }
                    items(CategoriaFaq.entries) { categoria ->
                        val count = MockData.faqs.count { it.categoria == categoria }
                        LcChip(
                            label = categoria.label,
                            selected = categoriaSelecionada == categoria,
                            onClick = { categoriaSelecionada = categoria },
                            count = count
                        )
                    }
                }
            }
            itemsIndexed(perguntasFiltradas) { index, faq ->
                FaqRow(
                    numero = index + 1,
                    faq = faq,
                    expanded = perguntaAbertaId == faq.id,
                    onToggle = {
                        perguntaAbertaId = if (perguntaAbertaId == faq.id) null else faq.id
                    }
                )
            }
        }
    }
}

@Composable
private fun FaqRow(
    numero: Int,
    faq: PerguntaFrequente,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    if (expanded) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle),
            shape = RoundedCornerShape(16.dp),
            color = BrandTint,
            border = BorderStroke(1.5.dp, Brand)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "$numero.  ${faq.pergunta}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Ink,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp)
                    )
                    Icon(Icons.Filled.ExpandLess, contentDescription = null, tint = Brand)
                }
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Brand.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    faq.resposta,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Ink.copy(alpha = 0.75f)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Foi útil?", style = MaterialTheme.typography.bodySmall, color = Muted)
                    Spacer(modifier = Modifier.width(10.dp))
                    FeedbackIconButton(icon = Icons.Filled.ThumbUp, tint = Brand, contentDescription = "Útil")
                    Spacer(modifier = Modifier.width(6.dp))
                    FeedbackIconButton(icon = Icons.Filled.ThumbDown, tint = Faint, contentDescription = "Não útil")
                }
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "$numero.  ${faq.pergunta}",
                style = MaterialTheme.typography.bodyLarge,
                color = Ink,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Filled.ExpandMore, contentDescription = null, tint = Faint)
        }
    }
}

@Composable
private fun FeedbackIconButton(
    icon: ImageVector,
    tint: Color,
    contentDescription: String
) {
    Surface(
        modifier = Modifier
            .size(30.dp)
            .clickable(onClick = {}),
        shape = CircleShape,
        color = White
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier
                .padding(6.dp)
                .size(18.dp)
        )
    }
}
