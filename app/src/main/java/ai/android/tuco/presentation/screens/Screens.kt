package ai.android.tuco.presentation.screens

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Settings : Screen("settings")
    object About : Screen("about")
}