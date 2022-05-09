package com.excelsior.codechallenge.infrastructure.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatUtils {

    fun formatDateToLocaleDate(date: String, locale: Locale? = null): String =
        getDateFromString(date)?.let {
            DateFormat.getDateInstance(DateFormat.LONG, locale?.let { it } ?: Locale.getDefault()).format(it)
        } ?: "Error due formatting date"

    fun getDateFromString(dateString: String): Date? {
        var date: Date? = null
        try {
            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(
                dateString
            )
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }
}