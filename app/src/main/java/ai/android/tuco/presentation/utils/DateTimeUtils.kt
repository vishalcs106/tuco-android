package ai.android.tuco.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatTime(timestamp: Long): String {
    try {
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }catch (e: Exception){
        return ""
    }
}

/**
 * Formats a given Date object into a detailed timestamp with date (e.g., "Today at 08:30 AM")
 */
fun formatDetailedTimestamp(timestamp: Long): String {
    val today = Calendar.getInstance()
    val messageTime = Calendar.getInstance().apply { timeInMillis = timestamp }

    return when {
        today.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == messageTime.get(Calendar.DAY_OF_YEAR) ->
            "Today at ${formatTime(timestamp)}"

        today.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) - messageTime.get(Calendar.DAY_OF_YEAR) == 1 ->
            "Yesterday at ${formatTime(timestamp)}"

        else -> {
            val fullDateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            fullDateFormat.format(Date(timestamp))
        }
    }
}