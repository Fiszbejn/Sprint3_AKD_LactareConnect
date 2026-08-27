package com.davifiszbejn557716.lactareconnect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted
import com.davifiszbejn557716.lactareconnect.ui.theme.White

@Composable
fun LcBrandMark(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier
                .size(28.dp)
                .background(Brand, CircleShape),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(14.dp)
            )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text("Lactare", style = MaterialTheme.typography.titleMedium, color = Ink)
            Text(
                "CONNECT",
                style = MaterialTheme.typography.labelSmall,
                color = Muted
            )
        }
    }
}
