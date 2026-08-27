package com.davifiszbejn557716.lactareconnect.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadge
import com.davifiszbejn557716.lactareconnect.ui.components.LcBadgeTone
import com.davifiszbejn557716.lactareconnect.ui.components.LcBrandMark
import com.davifiszbejn557716.lactareconnect.ui.components.LcButton
import com.davifiszbejn557716.lactareconnect.ui.components.LcButtonVariant
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandLight
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@Composable
fun WelcomeScreen(
    onQueroSerDoadora: () -> Unit,
    onJaTenhoCadastro: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LcBrandMark()
                LcBadge(text = "PT-BR", tone = LcBadgeTone.Neutral)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        brush = Brush.verticalGradient(listOf(BrandLight, Brand)),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Cada gota\ntem um destino ")
                        withStyle(SpanStyle(color = Brand)) { append("especial.") }
                    },
                    style = MaterialTheme.typography.displayLarge,
                    color = Ink
                )
                Text(
                    text = "Doar leite humano ajuda bebês que mais precisam a ter um futuro mais saudável. Você não está sozinha nessa jornada.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Muted
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LcButton(
                    text = "Quero ser uma pessoa doadora",
                    onClick = onQueroSerDoadora,
                    variant = LcButtonVariant.Primary
                )
                Text(
                    text = "Já tenho cadastro",
                    style = MaterialTheme.typography.labelLarge,
                    color = Brand,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onJaTenhoCadastro)
                )
                Text(
                    text = "Aprovado pelo Banco de Leite Humano",
                    style = MaterialTheme.typography.bodySmall,
                    color = Muted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}
