package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.MutableLiveData
import com.excelsior.codechallenge.eventsOverview.repository.EventsRepository
import com.excelsior.codechallenge.eventsOverview.utils.EventsOverviewSorting
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.network.response.ApiResponse
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import com.excelsior.codechallenge.infrastructure.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EventsOverviewAndroidViewModel(
    val eventsOverviewSorting: EventsOverviewSorting,
    val ioScope: CoroutineScope,
    val eventsRepository: EventsRepository
) : BaseAndroidViewModel(), EventsOverviewViewModel {

    override val eventsLiveData = MutableLiveData<List<Event>>()

    override var events: List<Event> = mutableListOf()

    override var eventDetail: Event? = null

    override val clickedEventLiveData = SingleLiveEvent<Event>()

    override val eventDetailLiveData = MutableLiveData<Event>()

    override val titleUpdateLiveData = MutableLiveData<Pair<Event, Event>>()

    override fun initiateRequestEvents() {
        loader.postValue(true)
        ioScope.launch {
            eventsRepository.initiateRequestEvents().apply {
                when (this) {
                    is ApiResponse.Success -> {
                        events = this.data
                        initiateUpdateLiveData(events)
                    }
                    is ApiResponse.Error -> {
                        initiatePostError(this.exception)
                    }
                }
            }
        }
    }

    override fun initiateRequestEventDetails(event: Event) {
        loader.postValue(true)
        ioScope.launch {
            eventsRepository.initiateRequestEventDetail(event.id).apply {
                when (this) {
                    is ApiResponse.Success -> {
                        eventDetail = this.data
                        initiateUpdateEventDetailLiveData(eventDetail)
                    }
                    is ApiResponse.Error -> {
                        initiatePostError(this.exception)
                    }
                }
            }
        }
    }

    fun initiatePostError(exception: Exception) {
        showError.postValue(exception.message)
    }

    override fun initiateFilterPriceAscending() {
        initiateUpdateLiveData(eventsOverviewSorting.initiatePriceSortingAscending(events))
    }

    override fun initiateFilterPriceDescending() {
        initiateUpdateLiveData(eventsOverviewSorting.initiatePriceSortingDescending(events))
    }

    override fun initiateFilterDateAscending() {
        initiateUpdateLiveData(eventsOverviewSorting.initiateDateSortingAscending(events))
    }

    override fun initiateFilterDateDescending() {
        initiateUpdateLiveData(eventsOverviewSorting.initiateDateSortingDescending(events))
    }

    override fun initiateFilterOutdatedEvents() {
        initiateUpdateLiveData(eventsOverviewSorting.initiateFilterOutdatedEvents(events))
    }

    fun initiateUpdateLiveData(events: List<Event>) {
        initiateUpdateTitle(events)
        eventsLiveData.postValue(events)
        loader.postValue(false)
    }

    fun initiateUpdateTitle(events: List<Event>) {
        eventsOverviewSorting.initiateDateSortingAscending(events).apply {
            titleUpdateLiveData.postValue(Pair(this.get(0), this.get(this.size - 1)))
        }
    }

    fun initiateUpdateEventDetailLiveData(event: Event?) {
        eventDetailLiveData.postValue(event)
        loader.postValue(false)
    }
}