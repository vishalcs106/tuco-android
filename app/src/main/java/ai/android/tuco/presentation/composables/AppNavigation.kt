package ai.android.tuco.presentation.composables
import ai.android.tuco.presentation.screens.ChatScreen
import ai.android.tuco.presentation.screens.HomeScreen
import ai.android.tuco.presentation.screens.Screen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, innerPadding: Modifier) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = innerPadding,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth }, // Slide in from right
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth }, // Slide out to left
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth }, // Slide in from left when going back
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth }, // Slide out to right when going back
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(Screen.Home.route) {
            HomeScreen { navController.navigate(Screen.Chat.route) }
        }
        composable(Screen.Chat.route) {
            ChatScreen()
        }
    }
}
