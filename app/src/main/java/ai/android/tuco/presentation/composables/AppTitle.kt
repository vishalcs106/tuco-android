package ai.android.tuco.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp


@Composable
fun TucoTitle(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() }
    ) {

        Text(
            text = "tuco.ai",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}


// Custom Gradient Extension for Text
@Composable
fun Modifier.gradientText(colors: List<Color>, angle: Float = 0f): Modifier {
    return this.drawWithContent {
        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset.Zero,
            end = Offset(size.width, size.height * kotlin.math.tan(Math.toRadians(angle.toDouble())).toFloat())
        )
        drawIntoCanvas { canvas ->
            canvas.saveLayer(bounds = size.toRect(), paint = Paint())
            drawContent()
            drawRect(brush, alpha = 1f)
        }
    }
}
