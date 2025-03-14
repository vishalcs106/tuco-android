package ai.android.tuco.presentation.screens

import ai.android.tuco.presentation.composables.GradientButton
import ai.android.tuco.presentation.composables.GradientFAB
import ai.android.tuco.presentation.composables.RegularText
import ai.android.tuco.ui.theme.unbounded
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Conversation(
    val title: String,
    val participants: String
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewChatCta: () -> Unit
) {
    val conversations =  remember { mutableStateListOf(
        Conversation("👨‍💻 Brainstorming Session", "You, Alex, Sam, tuco"),
        Conversation("📖 Fantasy Story Writing", "You, John, Maria, tuco"),
        Conversation("🎯 Marketing Campaign Ideas", "You, Olivia, David, tuco"),
        Conversation("🛫 Vacation Planning", "You, Mike, Lisa, tuco")
    ) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (conversations.isEmpty()) {
            // Show Empty State
            EmptyStateContent(onNewChatCta)
        } else {
            // Show Conversation List
            Box {
                ConversationList(conversations)

                GradientFAB(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    onClick = { onNewChatCta() }
                )
            }
        }
    }
}

@Composable
fun EmptyStateContent(onNewChatCta: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),  // Leaves space for CTA button
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hero Section
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
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        // CTA Button for New Conversation (Inside Empty State Content)
        GradientButton(
            onClick = { onNewChatCta() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "New Conversation"
        )
    }
}

@Composable
fun ConversationList(conversations: List<Conversation>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),  // Leaves space for CTA button
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(conversations) { conversation ->
            ConversationItem(conversation)
        }
    }
}

@Composable
fun ConversationItem(conversation: Conversation) {
    Surface(
        color = Color(0xFF181E30),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = conversation.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegularText(
                text = "Participants: ${conversation.participants}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun ConversationIdeasList(ideas: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
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
