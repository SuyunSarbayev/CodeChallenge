package com.excelsior.codechallenge.eventOverview

import com.excelsior.codechallenge.infrastructure.model.EventConverter
import com.excelsior.codechallenge.infrastructure.utils.FormatUtils
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class EventConverterTest {

    lateinit var eventConverter: EventConverter

    @Before
    fun setUp(){
        eventConverter = EventConverter(FormatUtils)
    }

    @Test
    fun initiateTestEventConverter(){
        var event = eventConverter.fromSource(eventMapped)
        assertEquals(event.id, "334fb861-b5bb-43cb-9601-b2cb348d3d65")
        assertEquals(event.name, "Equitox Equitax")
        assertEquals(event.ticketPrice, 100.0)
        assertEquals(event.date!!.time, CONST_TIME_DATE)
        assertEquals(event.email, "inagoodman@equitax.com")
        assertEquals(event.description, "description")
        assertEquals(event.phone, "+49 (960) 562-2931")
        assertEquals(event.address, "86976, Johnsonburg, Erskine Loop, 522")
        print(event)
    }

    companion object{
        const val CONST_TIME_DATE = 1641016800218
        const val CONST_TIME = "2022-01-01T12:00:00.218Z"

        var eventMapped = mapOf<String, Any?>(
            "guid" to "334fb861-b5bb-43cb-9601-b2cb348d3d65",
            "event" to "Equitox Equitax",
            "ticketPrice" to 100.0,
            "date" to CONST_TIME,
            "email" to "inagoodman@equitax.com",
            "description" to "description",
            "phone" to "+49 (960) 562-2931",
            "address" to "86976, Johnsonburg, Erskine Loop, 522"
        )
    }
}