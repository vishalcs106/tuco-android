package ai.android.tuco.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ParticipantsBottomSheet(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RegularText(
            text = "Participants", fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Sample participants
        val participants = listOf("Alice", "Bob", "Charlie", "Dave")

        participants.forEach { participant ->
            RegularText(
                text = participant,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

    }
}
