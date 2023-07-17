package com.example.newsapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object DateUtil {
    fun formatDateString(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX")
        val dateTime = LocalDateTime.parse(dateString, formatter)

        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
            .withLocale(Locale.US)
            .format(dateTime)
    }
}