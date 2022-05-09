package com.excelsior.codechallenge.eventOverview

import com.excelsior.codechallenge.infrastructure.utils.FormatUtils
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class FormatUtilsTest() {

    @Test
    fun initiateTestDateToStringTest(){
        var date = FormatUtils.getDateFromString(CONST_TIME)
        assertEquals(date!!.time, CONST_TIME_DATE)
    }

    @Test
    fun initiateTestDateToLocaleDate(){
        var date = FormatUtils.formatDateToLocaleDate(CONST_TIME, Locale.US)
        assertEquals(date, CONST_DATE_STRING_FORMAT)
    }

    companion object{
        const val CONST_TIME_DATE = 1641016800218
        const val CONST_TIME = "2022-01-01T12:00:00.218Z"
        const val CONST_DATE_STRING_FORMAT = "January 1, 2022"
    }
}