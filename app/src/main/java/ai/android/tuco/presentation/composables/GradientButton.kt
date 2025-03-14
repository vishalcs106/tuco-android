package ai.android.tuco.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ButtonSize(val padding: Int, val textSize: Int, val cornerRadius: Int) {
    SMALL(padding = 6, textSize = 12, cornerRadius = 8),
    MEDIUM(padding = 10, textSize = 14, cornerRadius = 12),
    LARGE(padding = 14, textSize = 16, cornerRadius = 16)
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.MEDIUM // Default to Medium
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size.cornerRadius.dp))  // Rounded Corners
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF007ACC),  // Electric Blue
                        Color(0xFFe81cff)   // Magenta Pink
                    )
                )
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = size.textSize.sp  // Dynamic Text Size
            ),
            modifier = Modifier.padding(
                vertical = size.padding.dp,
                horizontal = size.padding.dp * 2 // Wider padding for horizontal
            )
        )
    }
}

