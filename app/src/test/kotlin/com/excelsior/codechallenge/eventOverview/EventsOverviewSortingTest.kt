package com.excelsior.codechallenge.eventOverview

import com.excelsior.codechallenge.eventsOverview.utils.EventsOverviewSorting
import com.excelsior.codechallenge.infrastructure.model.Event
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import java.util.*

class EventsOverviewSortingTest {

    var eventsOverviewSorting: EventsOverviewSorting = EventsOverviewSorting()

    @Test
    fun initiateSortPriceAscendingTest(){
        var sortedList = eventsOverviewSorting.initiatePriceSortingAscending(initiateCreateTestEvents())
        assertThat(sortedList, isPriceAscendingOrdering())
    }

    @Test
    fun initiateSortPriceDescendingTest(){
        var sortedList = eventsOverviewSorting.initiatePriceSortingDescending(initiateCreateTestEvents())
        assertThat(sortedList, isPriceDescendingOrdering())
    }

    @Test
    fun initiateSortDateAscendingTest(){
        var sortedList = eventsOverviewSorting.initiateDateSortingAscending(initiateCreateTestEvents())
        assertThat(sortedList, isDateAscendingOrdering())
    }

    @Test
    fun initiateSortDateDescendingTest(){
        var sortedList = eventsOverviewSorting.initiateDateSortingDescending(initiateCreateTestEvents())
        assertThat(sortedList, isDateDescendingOrdering())
    }

    @Test
    fun initiateFilterOutdatedEventsTest(){
        var tests = initiateCreateTestEvents()
        var sortedList = eventsOverviewSorting.initiateFilterOutdatedEvents(initiateCreateTestEvents())
        assertThat(sortedList, isOnlyFutureDate(Calendar.getInstance().time))
    }

    private fun isPriceDescendingOrdering(): Matcher<in List<Event>?>? {
        return object : TypeSafeMatcher<List<Event>?>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: List<Event>?): Boolean {
                item?.let {
                    for (i in 0 until it.size - 1) {
                        if (it.get(i).ticketPrice < it.get(i + 1).ticketPrice) return false
                    }
                }
                return true
            }
        }
    }

    private fun isPriceAscendingOrdering(): Matcher<in List<Event>?>? {
        return object : TypeSafeMatcher<List<Event>?>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: List<Event>?): Boolean {
                item?.let {
                    for (i in 0 until it.size - 1) {
                        if (it.get(i).ticketPrice > it.get(i + 1).ticketPrice) return false
                    }
                }
                return true
            }
        }
    }

    private fun isDateAscendingOrdering(): Matcher<in List<Event>?>? {
        return object : TypeSafeMatcher<List<Event>?>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: List<Event>?): Boolean {
                item?.let {
                    for (i in 0 until it.size - 1) {
                        if (it.get(i).date!!.time > it.get(i + 1).date!!.time) return false
                    }
                }
                return true
            }
        }
    }

    private fun isDateDescendingOrdering(): Matcher<in List<Event>?>? {
        return object : TypeSafeMatcher<List<Event>?>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: List<Event>?): Boolean {
                item?.let {
                    for (i in 0 until it.size - 1) {
                        if (it.get(i).date!!.time < it.get(i + 1).date!!.time) return false
                    }
                }
                return true
            }
        }
    }

    private fun isOnlyFutureDate(currentDate: Date): Matcher<in List<Event>?>? {
        return object : TypeSafeMatcher<List<Event>?>() {

            override fun describeTo(description: Description?) {}

            override fun matchesSafely(item: List<Event>?): Boolean {
                item?.let {
                    for (i in 0 until it.size - 1) {
                        if (it.get(i).date!!.time < currentDate.time) return false
                    }
                }
                return true
            }
        }
    }

    fun initiateCreateTestEvents(): List<Event> {
        var currentTime = Calendar.getInstance()
        currentTime.add(Calendar.HOUR, -1)
        var timeOne = currentTime.time
        currentTime.add(Calendar.HOUR, +1)
        var timeTwo= currentTime.time
        currentTime.add(Calendar.HOUR, -2)
        var timeThree= currentTime.time
        currentTime.add(Calendar.HOUR, -3)
        var timeFour = currentTime.time
        currentTime.add(Calendar.HOUR, +4)
        var timeFive= currentTime.time

        return mutableListOf(
            Event(
                id = "11111111",
                name = "Test",
                ticketPrice = 150.0,
                ticketPriceString = "",
                formattedDate = "",
                date = timeOne,
                description = "",
                phone = "",
                email = "",
                address = ""
            ),
            Event(
                id = "11111111",
                name = "Test",
                ticketPrice = 600.0,
                ticketPriceString = "",
                formattedDate = "",
                date = timeTwo,
                description = "",
                phone = "",
                email = "",
                address = ""
            ),
            Event(
                id = "11111111",
                name = "Test",
                ticketPrice = 200.0,
                ticketPriceString = "",
                formattedDate = "",
                date = timeThree,
                description = "",
                phone = "",
                email = "",
                address = ""
            ),
            Event(
                id = "11111111",
                name = "Test",
                ticketPrice = 300.0,
                ticketPriceString = "",
                formattedDate = "",
                date = timeFour,
                description = "",
                phone = "",
                email = "",
                address = ""
            ),
            Event(
                id = "11111111",
                name = "Test",
                ticketPrice = 100.0,
                ticketPriceString = "",
                formattedDate = "",
                date = timeFive,
                description = "",
                phone = "",
                email = "",
                address = ""
            ),
        )
    }
}