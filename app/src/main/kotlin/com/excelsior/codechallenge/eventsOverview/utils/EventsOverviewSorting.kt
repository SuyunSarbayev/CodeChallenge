package com.excelsior.codechallenge.eventsOverview.utils

import com.excelsior.codechallenge.infrastructure.model.Event
import java.util.*

class EventsOverviewSorting {

    fun initiatePriceSortingAscending(events: List<Event>): List<Event> {
        return events.sortedBy { event -> event.ticketPrice }
    }

    fun initiatePriceSortingDescending(events: List<Event>): List<Event> {
        return events.sortedByDescending { event -> event.ticketPrice }
    }

    fun initiateDateSortingAscending(events: List<Event>): List<Event> {
        return events.sortedBy { event -> event.date }
    }

    fun initiateDateSortingDescending(events: List<Event>): List<Event> {
        return events.sortedByDescending { event -> event.date }
    }

    fun initiateFilterOutdatedEvents(events: List<Event>): List<Event> {
        return events
            .filter { event -> Calendar.getInstance().time.before(event.date) }
            .sortedBy { event -> event.date }
    }
}