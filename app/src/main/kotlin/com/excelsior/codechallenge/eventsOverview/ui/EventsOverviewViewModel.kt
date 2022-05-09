package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.MutableLiveData
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.ui.BaseViewModel
import com.excelsior.codechallenge.infrastructure.utils.SingleLiveEvent

interface EventsOverviewViewModel : BaseViewModel {

    val eventsLiveData: MutableLiveData<List<Event>>
        get() = MutableLiveData<List<Event>>()

    val clickedEventLiveData: SingleLiveEvent<Event>

    val eventDetailLiveData: MutableLiveData<Event>
        get() = MutableLiveData<Event>()

    val titleUpdateLiveData: MutableLiveData<Pair<Event, Event>>
        get() = MutableLiveData<Pair<Event, Event>>()

    var events: List<Event>

    var eventDetail: Event?

    fun initiateRequestEvents()

    fun initiateRequestEventDetails(event: Event)

    fun initiateFilterPriceAscending()

    fun initiateFilterPriceDescending()

    fun initiateFilterDateAscending()

    fun initiateFilterDateDescending()

    fun initiateFilterOutdatedEvents()
}