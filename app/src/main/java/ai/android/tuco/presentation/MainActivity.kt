package ai.android.tuco.presentation

import ai.android.tuco.BuildConfig
import ai.android.tuco.R
import ai.android.tuco.presentation.composables.GradientButton
import ai.android.tuco.presentation.composables.RegularText
import ai.android.tuco.presentation.composables.StyledStatusBar
import ai.android.tuco.presentation.composables.TucoTitle
import ai.android.tuco.presentation.screens.ChatScreen
import ai.android.tuco.presentation.screens.HomeScreen
import ai.android.tuco.presentation.screens.Screen
import ai.android.tuco.ui.theme.TucoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TucoTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    StyledStatusBar(color = Color.Black, isLight = false)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent { route ->
                scope.launch { drawerState.close() }
                navController.navigate(route)
            }
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,  // 🔹 Ensures consistent background
            topBar = {
                Column {
                    TopAppBar(
                        title = { TucoTitle{navController.navigate(Screen.Home.route)} },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_menu),
                                    contentDescription = "Gradient Icon",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    )
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f) // Subtle Divider
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // 🔹 Ensures content matches theme
        ) { innerPadding ->
            NavHost(navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)){
                composable(
                    route = Screen.Home.route,
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth }, // Enter from right
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth }, // Exit to left
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                ) {
                    HomeScreen { navController.navigate(Screen.Chat.route) }
                }

                composable(
                    route = Screen.Chat.route,
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth }, // Enter from right
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth }, // Exit to left
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                ) {
                    ChatScreen()
                }

            }
        }
    }
}


@Composable
fun DrawerContent(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        GradientButton(
            text = "Login",
            onClick = {},
            modifier = Modifier,
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {  onNavigate(Screen.Home.route) }) { RegularText("Home") }
        TextButton(onClick = {  onNavigate(Screen.Chat.route) }) { RegularText("New Conversation") }
        TextButton(onClick = {  onNavigate(Screen.Settings.route) }) { RegularText("Settings") }
        TextButton(onClick = {  onNavigate(Screen.About.route) }) { RegularText("About") }

        // Push the app version to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Display App Version
        Text(
            text = "Version ${BuildConfig.VERSION_NAME}", // Dynamically fetches version name
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f), // Subtle shade
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TucoTheme {
        MainScreen()
    }
}
