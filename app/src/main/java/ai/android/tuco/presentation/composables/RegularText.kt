package ai.android.tuco.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun RegularText(
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 14.sp
) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = color, fontSize = fontSize)
}