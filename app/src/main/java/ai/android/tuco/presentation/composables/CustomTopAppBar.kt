package ai.android.tuco.presentation.composables

import ai.android.tuco.presentation.screens.Screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    onMenuClick: () -> Unit,
    onInviteClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: Screen.Home.route

    val isHomeScreen = currentRoute == Screen.Home.route
    val isChatScreen = currentRoute == Screen.Chat.route

    Column {
        TopAppBar(
            title = { if (isHomeScreen) TucoTitle { onMenuClick() } else RegularText( "Chat", fontSize = 20.sp) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (isHomeScreen) {
                            onMenuClick()  // Open drawer on Home Screen
                        } else {
                            navController.popBackStack() // Go back on other screens
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (isHomeScreen) Icons.Default.Menu else Icons.Default.ArrowBack,
                        contentDescription = if (isHomeScreen) "Menu" else "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            actions = {
                if (isChatScreen) {
                    Box(modifier = Modifier.padding(end = 8.dp)){
                        Row ( verticalAlignment = Alignment.CenterVertically){
                            GradientButton(text = "Invite", onClick = {onInviteClick()}, size = ButtonSize.SMALL)
                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Info",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable { onInfoClick() }
                            )

                        }
                    }
                }
            }
        )
        HorizontalDivider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
    }
}
