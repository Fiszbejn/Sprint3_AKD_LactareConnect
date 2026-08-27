package com.davifiszbejn557716.lactareconnect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandTint
import com.davifiszbejn557716.lactareconnect.ui.theme.Faint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Line
import com.davifiszbejn557716.lactareconnect.ui.theme.White
import java.text.NumberFormat
import java.util.Locale

@Composable
fun LcCard(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(16.dp),
    borderColor: Color = Line,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = White,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(modifier = Modifier.padding(padding)) { content() }
    }
}

@Composable
fun LcChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    count: Int? = null
) {
    Surface(
        modifier = modifier.selectable(selected = selected, onClick = onClick),
        shape = RoundedCornerShape(999.dp),
        color = if (selected) Brand else White,
        border = BorderStroke(1.dp, if (selected) Brand else Line)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (count != null) "$label ($count)" else label,
                style = MaterialTheme.typography.bodyMedium,
                color = if (selected) White else Ink
            )
        }
    }
}

enum class LcBadgeTone { Brand, Success, Warning, Danger, Neutral }

@Composable
fun LcBadge(
    text: String,
    modifier: Modifier = Modifier,
    tone: LcBadgeTone = LcBadgeTone.Brand
) {
    val (bg, fg) = when (tone) {
        LcBadgeTone.Brand -> BrandTint to Brand
        LcBadgeTone.Success -> Color(0xFFDDF3EF) to Color(0xFF1B7F79)
        LcBadgeTone.Warning -> Color(0xFFFCEFD6) to Color(0xFFB8790C)
        LcBadgeTone.Danger -> Color(0xFFFFE3E6) to Color(0xFFCC1F30)
        LcBadgeTone.Neutral -> Color(0xFFF0F0EE) to Faint
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = bg
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = fg,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

enum class CoinSize { Sm, Md, Lg }

@Composable
fun Coin(
    value: Int,
    modifier: Modifier = Modifier,
    dark: Boolean = false,
    size: CoinSize = CoinSize.Md
) {
    val (bg, fg) = if (dark) Color.White.copy(alpha = 0.16f) to White else BrandTint to Brand
    val fontStyle = when (size) {
        CoinSize.Sm -> MaterialTheme.typography.labelMedium
        CoinSize.Md -> MaterialTheme.typography.titleMedium
        CoinSize.Lg -> MaterialTheme.typography.headlineLarge
    }
    val iconSize = when (size) {
        CoinSize.Sm -> 14.dp
        CoinSize.Md -> 18.dp
        CoinSize.Lg -> 26.dp
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = bg
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.WaterDrop,
                contentDescription = null,
                tint = fg,
                modifier = Modifier.size(iconSize)
            )
            Text(
                text = "  " + NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR")).format(value),
                style = fontStyle,
                color = fg
            )
        }
    }
}

@Composable
fun Avatar(
    initials: String,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = 48.dp,
    background: Color = BrandTint,
    contentColor: Color = Brand
) {
    Box(
        modifier = modifier
            .size(size)
            .background(background, CircleShape)
            .border(0.dp, Color.Transparent, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = initials, color = contentColor, style = MaterialTheme.typography.titleMedium)
    }
}
