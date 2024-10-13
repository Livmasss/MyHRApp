package com.example.coreui.utils

import android.icu.text.SimpleDateFormat
import java.util.Date

object MyDateTimeFormatter {
    private val formatter = SimpleDateFormat("dd MMMM")

    fun formatPublishDate(date: Date): String =
        formatter.format(date)
}