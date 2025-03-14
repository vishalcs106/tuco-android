package ai.android.tuco.presentation.screens


import ai.android.tuco.presentation.utils.formatTime
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatScreen() {
    var messageInput by remember { mutableStateOf(TextFieldValue("")) }
    val messages = remember {
        mutableStateListOf(
            Message("👋 Welcome to Tuco.ai!", isFromUser = false, senderName = "tuco", timestamp = formatTime(System.currentTimeMillis())),
            Message("🧠 Tuco can remember conversations and offer insightful suggestions.", isFromUser = false, senderName = "tuco", timestamp = formatTime(System.currentTimeMillis())),
            Message("🤝 Invite your friends and brainstorm ideas together with Tuco!", isFromUser = false, senderName = "tuco", timestamp = formatTime(System.currentTimeMillis())),
            Message("💬 Tag @tuco anytime in the chat to ask me questions or get suggestions.", isFromUser = false, senderName = "tuco", timestamp = formatTime(System.currentTimeMillis())),
            Message("🎯 Let's get started! Ask me anything.", isFromUser = false, senderName = "tuco", timestamp = formatTime(System.currentTimeMillis())),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.ime)
    ) {
        // Chat History (LazyColumn ensures bottom-alignment)
        LazyColumn(
            modifier = Modifier
                .weight(1f) // ✅ Fix: Prevents infinite height issue
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true // ✅ Ensures messages start from the bottom
        ) {
            items(messages.size) { index ->
                ChatBubble(messages[messages.size - 1 - index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Box(
            modifier = Modifier.padding()
                .fillMaxWidth()
                .drawWithCache {
                    val gradient = Brush.linearGradient(
                        colors = listOf(Color(0xFF007ACC), Color(0xFFE81CFF))
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRoundRect(
                            brush = gradient,
                            size = size,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx()),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(0.5.dp.toPx())
                        )
                    }
                }
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(24.dp))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 8.dp)) {
                BasicTextField(
                    value = messageInput,
                    onValueChange = { messageInput = it },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences, // ✅ Sentence capitalization
                        autoCorrect = true                                 // ✅ Auto-correct enabled
                    ),
                    minLines = 2,
                    cursorBrush = SolidColor(Color.White)
                )
                IconButton(
                    onClick = {
                        if (messageInput.text.isNotEmpty()) {
                            val currentTime = System.currentTimeMillis()
                            messages.add(Message(messageInput.text, isFromUser = true,
                                timestamp = formatTime(currentTime)))
                            messages.add(
                                Message("Great idea! What else would you like to explore?",
                                    isFromUser = false, senderName = "tuco")
                            )
                            messageInput = TextFieldValue("") // Clear input after sending
                        }
                    },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF007ACC),  // Electric Blue
                                        Color(0xFFE81CFF)   // Vivid Magenta
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)

                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Default.Send,
                            contentDescription = "New Conversation",
                            tint = Color.White // Icon color for better contrast
                        )
                    }
                }
            }

        }
    }
}



@Composable
fun ChatBubble(message: Message) {
    // Improved Solid Colors
    val userBubbleColor = Color(0xFF007ACC) // Calm Blue for User
    val tucoBubbleColor = Color(0xFF181E30) // Charcoal Black for Tuco

    val bubbleColor = if (message.isFromUser) userBubbleColor else tucoBubbleColor
    val textColor = if (message.isFromUser) Color.White else Color(0xFFECECEC) // Light Grey for visibility
    val senderDisplayName = if (message.isFromUser) "You" else message.senderName

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
    ) {
        Text(
            text = senderDisplayName,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.height(2.dp))

        // Solid Color Chat Bubble
        Surface(
            color = bubbleColor,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isFromUser) 16.dp else 0.dp,
                bottomEnd = if (message.isFromUser) 0.dp else 16.dp
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)

        ) {
            Text(
                text = message.text,
                color = textColor,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = message.timestamp,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}







// Sample Message Data Class
data class Message(
    val text: String,
    val isFromUser: Boolean,
    val senderName: String = "You",  // Default to "You" for user's messages
    val timestamp: String = ""
)

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen()
    }
}
