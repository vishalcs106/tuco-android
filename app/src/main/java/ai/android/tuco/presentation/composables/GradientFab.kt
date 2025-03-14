package ai.android.tuco.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientFAB(onClick: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(56.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF007ACC),  // Electric Blue
                        Color(0xFFE81CFF)   // Vivid Magenta
                    )
                ),
                shape = RoundedCornerShape(12.dp)

            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "New Conversation",
            tint = Color.White // Icon color for better contrast
        )
    }
}