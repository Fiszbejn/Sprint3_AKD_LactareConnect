package com.davifiszbejn557716.lactareconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.davifiszbejn557716.lactareconnect.ui.navigation.LactareConnectApp
import com.davifiszbejn557716.lactareconnect.ui.theme.LactareConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LactareConnectTheme {
                LactareConnectApp()
            }
        }
    }
}

