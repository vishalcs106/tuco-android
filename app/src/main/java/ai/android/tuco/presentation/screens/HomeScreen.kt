package ai.android.tuco.presentation.screens

import ai.android.tuco.presentation.composables.GradientButton
import ai.android.tuco.presentation.composables.RegularText
import ai.android.tuco.ui.theme.unbounded
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hero Section
        Text(
            text = "tuco.ai - your wingman",
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontFamily = unbounded,
                fontSize = 22.sp
            )
        )
        RegularText(
            text = "Free. Private. Contextual. Collaborative.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Flex Box for Suggested Conversations
        RegularText(
            text = "Try asking Tuco about:",
            fontSize = 20.sp,
            color = Color.White
        )

        ConversationIdeasGrid(
            ideas = listOf(
                "Brainstorm ideas",
                "Plan a vacation",
                "Content creation tips",
                "AI-powered jokes",
                "Fitness routines",
                "Cooking recipes",
                "Coding help",
                "Book recommendations"
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // CTA Button for New Conversation
        GradientButton(
            onClick = { onNewChatCta() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "New Conversation"
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ConversationIdeasGrid(ideas: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        maxItemsInEachRow = 3, // Flexible grid structure
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ideas.forEach { idea ->
            SuggestionChip(text = idea)
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
