package com.excelsior.codechallenge.eventOverview

import com.excelsior.codechallenge.eventsOverview.repository.EventsRepository
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewAndroidViewModel
import com.excelsior.codechallenge.eventsOverview.utils.EventsOverviewSorting
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.network.response.ApiResponse
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*
import kotlin.test.assertEquals


class EventsDetailFragmentTest {

    lateinit var viewModel: EventsOverviewAndroidViewModel// = mock(EventsOverviewAndroidViewModel::class.java)

    @MockK
    var eventsRepository: EventsRepository = Mockito.mock(EventsRepository::class.java)

    lateinit var ioScope: CoroutineScope

    @Before
    fun setup() {
        ioScope = createCoroutineScope()
        MockitoAnnotations.initMocks(this)
        viewModel = EventsOverviewAndroidViewModel(
            EventsOverviewSorting(),
            ioScope,
            eventsRepository
        )
    }

    private fun createCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Test
    fun initiateRequestEventDetailTestSuccess(){
        var mockEvent = ApiResponse.Success(initiateCreateTestEvent())

        ioScope.launch {
            `when`(eventsRepository.initiateRequestEventDetail("1111")).thenReturn(mockEvent)
            viewModel.initiateRequestEventDetails(mockEvent.data)
            assertEquals(viewModel.eventDetail, mockEvent.data)
        }
    }

    fun initiateCreateTestEvent(): Event {
        var currentTime = Calendar.getInstance()

        return Event(
            id = "test",
            name = "test",
            ticketPrice = 100.0,
            ticketPriceString = "test",
            formattedDate = "test",
            date = currentTime.time,
            description = "test",
            phone = "test",
            email = "test",
            address = "test"
        )
    }
}