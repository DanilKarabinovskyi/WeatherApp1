package danyil.karabinovskyi.weatherapp.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.convertToHour() : String {
    return (this.toInt() % 12).toString() + if (this.toInt() >= 12) "PM" else "AM"
}

fun Long.readTimestamp(): String {
    val formatter = SimpleDateFormat("hh:mm")
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000L
    return formatter.format(calendar.time)
}
