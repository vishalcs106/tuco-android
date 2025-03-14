package ai.android.tuco.presentation.screens

import ai.android.tuco.presentation.composables.GradientButton
import ai.android.tuco.presentation.composables.RegularText
import ai.android.tuco.ui.theme.unbounded
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onNewChatCta: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally // ✅ Center aligns content horizontally
            ) {
                Text(
                    text = "tuco.ai - your wingman",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = unbounded,
                        fontSize = 18.sp
                    )
                )

                RegularText(
                    text = "Free. Private. Contextual. Collaborative.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            RegularText(
                text = "Collaborate with tuco:",
                fontSize = 20.sp,
                color = Color.White
            )

            // Suggested Conversations List
            ConversationIdeasList(
                ideas = listOf(
                    "👫 Invite friends and roast each other",
                    "💬 Discuss your relationship with your partner",
                    "🧠 Brainstorm ideas with your creative buddy",
                    "📢 Plan your next viral content with friends",
                    "🎯 Debate hot topics with your social circle",
                    "🎬 Write a short film script together",
                    "📖 Co-write a fantasy story with AI",
                    "🛫 Plan your next vacation collaboratively"
                ),
                modifier = Modifier
                    .weight(1f)  // ✅ Expands to fill available space
            )
        }

        // CTA Button for New Conversation (Fixed at Bottom)
        GradientButton(
            onClick = { onNewChatCta() },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // ✅ Correct usage of `.align()` inside `Box`
                .padding(8.dp),
            text = "New Conversation"
        )
    }
}

@Composable
fun ConversationIdeasList(ideas: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(ideas) { idea ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SuggestionChip(text = idea)
            }
        }
    }
}


@Composable
fun SuggestionChip(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(4.dp)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            RegularText(
                text = text
            )
        }
    }
}
