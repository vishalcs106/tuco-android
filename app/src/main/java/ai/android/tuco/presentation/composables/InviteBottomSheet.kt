package ai.android.tuco.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat

@Composable
fun InviteBottomSheet(onClose: () -> Unit) {
    var emailInput by remember { mutableStateOf(TextFieldValue("")) }
    var isEmailValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF181E30))
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Invite Friends",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Invite your friends to join and collaborate with Tuco.",
            style = MaterialTheme.typography.bodyMedium
        )

        // Email Input Field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (emailInput.text.isEmpty()) {
                RegularText(
                    text = "Enter email address",  // ✅ Added Hint
                    fontSize = 14.sp
                )
            }

            BasicTextField(
                value = emailInput,
                onValueChange = {
                    emailInput = it
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Error Message for Invalid Email
        if (!isEmailValid && emailInput.text.isNotEmpty()) {
            Text(
                text = "Please enter a valid email address",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        GradientButton(
            text = "Invite",
            onClick = {
                isEmailValid = PatternsCompat.EMAIL_ADDRESS.matcher(emailInput.text).matches()  // ✅ Email Validation
                if (isEmailValid && emailInput.text.isNotEmpty()) {
                    // 🔹 Invite Logic Here (API Call / Email Service)
                    onClose()
                }
            }
        )

    }
}
