package com.example.coreui.utils

import android.icu.text.SimpleDateFormat
import java.util.Date

object MyDateTimeFormatter {
    private val formatter = SimpleDateFormat("d MMMM")

    fun formatPublishDate(date: Date): String =
        formatter.format(date)
}