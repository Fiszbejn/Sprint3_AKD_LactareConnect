package com.davifiszbejn557716.lactareconnect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.davifiszbejn557716.lactareconnect.ui.theme.AccentRed
import com.davifiszbejn557716.lactareconnect.ui.theme.Brand
import com.davifiszbejn557716.lactareconnect.ui.theme.BrandTint
import com.davifiszbejn557716.lactareconnect.ui.theme.Faint
import com.davifiszbejn557716.lactareconnect.ui.theme.Ink
import com.davifiszbejn557716.lactareconnect.ui.theme.Line
import com.davifiszbejn557716.lactareconnect.ui.theme.Muted

@Composable
fun LcInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    hint: String? = null,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isPassword: Boolean = false
) {
    var senhaVisivel by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Muted
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            placeholder = placeholder?.let { { Text(it, color = Faint) } },
            singleLine = singleLine,
            isError = error != null,
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else keyboardType
            ),
            visualTransformation = if (isPassword && !senhaVisivel) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(
                            imageVector = if (senhaVisivel) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                            tint = Faint
                        )
                    }
                }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Brand,
                unfocusedBorderColor = Line,
                errorBorderColor = AccentRed,
                focusedContainerColor = BrandTint,
                unfocusedContainerColor = Color.White
            )
        )
        val message = error ?: hint
        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = if (error != null) AccentRed else Muted,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
