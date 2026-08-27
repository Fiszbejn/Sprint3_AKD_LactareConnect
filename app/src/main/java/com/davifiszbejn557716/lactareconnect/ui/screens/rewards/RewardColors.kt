package com.davifiszbejn557716.lactareconnect.ui.screens.rewards

import androidx.compose.ui.graphics.Color
import com.davifiszbejn557716.lactareconnect.data.model.CorCard

fun corDe(cor: CorCard): Color = when (cor) {
    CorCard.NAVY -> Color(0xFF12305C)
    CorCard.LIGHT_BLUE -> Color(0xFF54B2E3)
    CorCard.TEAL -> Color(0xFF1B7F79)
    CorCard.PINK -> Color(0xFFF25CA2)
}
