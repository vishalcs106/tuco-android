package ai.android.tuco.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InviteBottomSheet(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Invite Friends",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Share this link with your friends to start collaborating:",
            style = MaterialTheme.typography.bodyMedium
        )

        GradientButton(
            text = "Copy Invite Link",
            onClick = {
                // Example logic for copying the invite link
                // Copy logic goes here (Clipboard logic)
                onClose()
            }
        )

        GradientButton(
            text = "Share via WhatsApp",
            onClick = {
                // Logic for sharing via WhatsApp
                onClose()
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Close Button
        GradientButton(
            text = "Close",
            onClick = { onClose() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}
