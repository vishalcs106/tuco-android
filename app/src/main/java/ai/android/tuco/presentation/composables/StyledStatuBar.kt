package ai.android.tuco.presentation.composables


import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StyledStatusBar(color: Color = Color.Black, isLight: Boolean = false) {
    val view = LocalView.current
    val window = (view.context as ComponentActivity).window

    SideEffect {
        window.statusBarColor = color.toArgb() // Sets the status bar color to Black
        WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = isLight
    }
}
