package com.davifiszbejn557716.lactareconnect.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandLight
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.White

// Marca: globo contornado com uma gota de leite ao centro ("alcance global da doação").
// Desenhado via Canvas (não vector drawable) para evitar incompatibilidade do parser
// de vetores do Compose com este toolchain do AGP.
@Composable
fun LcBrandMark(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(28.dp)) {
            scale(scale = size.width / 48f, pivot = Offset.Zero) {
                drawCircle(color = White, radius = 19f, center = Offset(24f, 24f))
                drawCircle(
                    color = Brand,
                    radius = 19f,
                    center = Offset(24f, 24f),
                    style = Stroke(width = 2.5f)
                )
                drawOval(
                    color = Brand.copy(alpha = 0.55f),
                    topLeft = Offset(5f, 17f),
                    size = Size(38f, 14f),
                    style = Stroke(width = 1.5f)
                )
                val meridian1 = Path().apply {
                    moveTo(24f, 5f)
                    cubicTo(16f, 14f, 16f, 34f, 24f, 43f)
                }
                drawPath(meridian1, color = Brand.copy(alpha = 0.55f), style = Stroke(width = 1.5f))
                val meridian2 = Path().apply {
                    moveTo(24f, 5f)
                    cubicTo(32f, 14f, 32f, 34f, 24f, 43f)
                }
                drawPath(meridian2, color = Brand.copy(alpha = 0.55f), style = Stroke(width = 1.5f))
                val drop = Path().apply {
                    moveTo(24f, 11f)
                    cubicTo(18f, 18f, 17f, 24f, 19f, 28f)
                    arcTo(Rect(19f, 23f, 29f, 33f), 180f, -180f, false)
                    cubicTo(31f, 24f, 30f, 18f, 24f, 11f)
                    close()
                }
                drawPath(drop, color = BrandLight)
                drawPath(drop, color = Brand, style = Stroke(width = 1.2f))
            }
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text("Lactare", style = MaterialTheme.typography.titleMedium, color = Ink)
            Text(
                "CONNECT",
                style = MaterialTheme.typography.labelSmall,
                color = Brand
            )
        }
    }
}
