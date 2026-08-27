package com.davifiszbejn557716.lactareconnect.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.davifiszbejn557716.lactareconnect.ui.screens.chat.ChatScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.conta.ContaScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.doar.AppointmentScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.doar.DoarMapScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.faq.HomeFaqScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.CadastroFormState
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.CadastroStep1Screen
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.CadastroStep2Screen
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.CadastroStep3Screen
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.LoginScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.onboarding.WelcomeScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.rewards.RewardDetailScreen
import com.davifiszbejn557716.lactareconnect.ui.screens.rewards.RewardsScreen

@Composable
fun LactareConnectApp() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val cadastroForm = remember { CadastroFormState() }

    Scaffold(
        bottomBar = {
            if (isBottomNavRoute(currentRoute)) {
                LcBottomBar(navController)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.WELCOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.WELCOME) {
                WelcomeScreen(
                    onQueroSerDoadora = { navController.navigate(Routes.CADASTRO_STEP1) },
                    onJaTenhoCadastro = { navController.navigate(Routes.LOGIN) }
                )
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    onEntrar = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.WELCOME) { inclusive = true }
                        }
                    },
                    onCadastrar = { navController.navigate(Routes.CADASTRO_STEP1) },
                    onVoltar = { navController.popBackStack() }
                )
            }
            composable(Routes.CADASTRO_STEP1) {
                CadastroStep1Screen(
                    form = cadastroForm,
                    onContinuar = { navController.navigate(Routes.CADASTRO_STEP2) },
                    onVoltar = { navController.popBackStack() }
                )
            }
            composable(Routes.CADASTRO_STEP2) {
                CadastroStep2Screen(
                    form = cadastroForm,
                    onContinuar = { navController.navigate(Routes.CADASTRO_STEP3) },
                    onVoltar = { navController.popBackStack() }
                )
            }
            composable(Routes.CADASTRO_STEP3) {
                CadastroStep3Screen(
                    form = cadastroForm,
                    onFinalizar = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.WELCOME) { inclusive = true }
                        }
                    },
                    onVoltar = { navController.popBackStack() }
                )
            }

            composable(Routes.HOME) {
                HomeFaqScreen()
            }
            composable(Routes.DOAR) {
                DoarMapScreen(onAgendar = { navController.navigate(Routes.AGENDAMENTO) })
            }
            composable(Routes.AGENDAMENTO) {
                AppointmentScreen(
                    onVoltar = { navController.popBackStack() },
                    onConfirmado = { navController.popBackStack(Routes.DOAR, inclusive = false) }
                )
            }
            composable(Routes.CHAT) {
                ChatScreen()
            }
            composable(Routes.RECOMPENSAS) {
                RewardsScreen(
                    onRecompensaSelecionada = { id ->
                        navController.navigate(Routes.recompensaDetalhe(id))
                    }
                )
            }
            composable(
                route = Routes.RECOMPENSA_DETALHE,
                arguments = listOf(navArgument("recompensaId") { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString("recompensaId").orEmpty()
                RewardDetailScreen(
                    recompensaId = id,
                    onVoltar = { navController.popBackStack() },
                    onResgatado = { navController.popBackStack() }
                )
            }
            composable(Routes.CONTA) {
                ContaScreen(
                    onSair = {
                        navController.navigate(Routes.WELCOME) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
