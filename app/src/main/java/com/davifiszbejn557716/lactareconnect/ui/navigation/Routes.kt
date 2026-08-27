package com.davifiszbejn557716.lactareconnect.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.ui.graphics.vector.ImageVector

object Routes {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val CADASTRO_STEP1 = "cadastro/1"
    const val CADASTRO_STEP2 = "cadastro/2"
    const val CADASTRO_STEP3 = "cadastro/3"

    const val HOME = "home"
    const val DOAR = "doar"
    const val CHAT = "chat"
    const val RECOMPENSAS = "recompensas"
    const val CONTA = "conta"

    const val AGENDAMENTO = "doar/agendamento"
    const val RECOMPENSA_DETALHE = "recompensas/detalhe/{recompensaId}"

    fun recompensaDetalhe(id: String) = "recompensas/detalhe/$id"
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Routes.HOME, "Início", Icons.Filled.Home, Icons.Outlined.Home),
    BottomNavItem(Routes.DOAR, "Doar", Icons.Filled.WaterDrop, Icons.Outlined.WaterDrop),
    BottomNavItem(Routes.CHAT, "Chat", Icons.Filled.ChatBubble, Icons.Outlined.ChatBubble),
    BottomNavItem(Routes.RECOMPENSAS, "Recompensas", Icons.Filled.CardGiftcard, Icons.Outlined.CardGiftcard),
    BottomNavItem(Routes.CONTA, "Conta", Icons.Filled.Person, Icons.Outlined.Person)
)
