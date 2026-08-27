package com.davifiszbejn557716.lactareconnect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandLight
import com.davifiszbejn557716.lactareconnect.ui.theme.Faint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Line
import com.davifiszbejn557716.lactareconnect.ui.theme.White
import androidx.compose.material3.Button as Material3Button

enum class LcButtonVariant { Primary, Secondary, Ghost, Accent }

@Composable
fun LcButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: LcButtonVariant = LcButtonVariant.Primary,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    compact: Boolean = false
) {
    val widthModifier = if (fullWidth) modifier.fillMaxWidth() else modifier
    val shape = RoundedCornerShape(12.dp)
    val heightModifier = if (compact) Modifier else Modifier.height(48.dp)
    val contentPadding = if (compact) {
        PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    } else {
        ButtonDefaults.ContentPadding
    }
    val textStyle = if (compact) MaterialTheme.typography.labelMedium else MaterialTheme.typography.labelLarge

    when (variant) {
        LcButtonVariant.Primary -> Material3Button(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(heightModifier),
            shape = shape,
            contentPadding = contentPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = Brand,
                contentColor = White,
                disabledContainerColor = Faint,
                disabledContentColor = White
            )
        ) { Text(text, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis) }

        LcButtonVariant.Accent -> Material3Button(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(heightModifier),
            shape = shape,
            contentPadding = contentPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandLight,
                contentColor = White,
                disabledContainerColor = Faint,
                disabledContentColor = White
            )
        ) { Text(text, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis) }

        LcButtonVariant.Secondary -> OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(heightModifier),
            shape = shape,
            contentPadding = contentPadding,
            border = BorderStroke(1.dp, Brand),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Brand)
        ) { Text(text, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis) }

        LcButtonVariant.Ghost -> OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(heightModifier),
            shape = shape,
            contentPadding = contentPadding,
            border = BorderStroke(1.dp, Line),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Ink)
        ) { Text(text, style = textStyle, maxLines = 1, overflow = TextOverflow.Ellipsis) }
    }
}

@Composable
fun LcTextAction(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Brand)
    }
}
