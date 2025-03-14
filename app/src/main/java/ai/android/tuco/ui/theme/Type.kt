package ai.android.tuco.ui.theme

import ai.android.tuco.R
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

// Google Fonts Configuration
private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val unboundedFontName = GoogleFont("Unbounded")

val unbounded = FontFamily(
    Font(
        googleFont = unboundedFontName,
        fontProvider = provider
    )
)

val firaSansFontName = GoogleFont("Fira Sans")

val firaSans = FontFamily(
    Font(
        googleFont = firaSansFontName,
        fontProvider = provider
    )
)

// Custom Typography
val TucoTypography = Typography(
    headlineLarge = TextStyle(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF007ACC), // Electric Blue
                Color(0xFFe81cff)  // Coral Orange
            )
        ),
        fontWeight = FontWeight.SemiBold,
        fontFamily = unbounded,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = firaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = firaSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)