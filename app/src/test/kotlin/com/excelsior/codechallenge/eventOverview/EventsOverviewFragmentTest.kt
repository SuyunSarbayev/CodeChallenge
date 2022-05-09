package com.excelsior.codechallenge.eventOverview

import com.excelsior.codechallenge.eventsOverview.repository.EventsRepository
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewAndroidViewModel
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewFragment
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.network.response.ApiResponse
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import java.util.*

class EventsOverviewFragmentTest {

    @Mock
    lateinit var view: EventsOverviewFragment

    @Mock
    lateinit var viewModel: EventsOverviewAndroidViewModel

    @MockK
    var eventsRepository: EventsRepository = Mockito.mock(EventsRepository::class.java)

    lateinit var ioScope: CoroutineScope

    @Before
    fun setup() {
        ioScope = createCoroutineScope()
        MockitoAnnotations.initMocks(this)
    }

    private fun createCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Test
    fun initiateRequestEventsTestSuccess(){
        var mockedList = ApiResponse.Success(initiateCreateTestEvents())

        ioScope.launch {
            doReturn(mockedList).`when`(eventsRepository).initiateRequestEvents()
            eventsRepository.initiateRequestEvents()
            val inOrder = Mockito.inOrder(view)
            inOrder.verify(view, times(1)).initializeTitle(mockedList.data.get(0), mockedList.data.get(1))
            inOrder.verify(view, times(1)).initializeAdapterItems(mockedList.data)
        }
    }

    @Test
    fun initiateRequestEventsTestError(){
        var mockedError = ApiResponse.Error<Exception>(Exception())
        ioScope.launch {
            doReturn(mockedError).`when`(eventsRepository).initiateRequestEvents()
            eventsRepository.initiateRequestEvents()
            val inOrder = Mockito.inOrder(view)
            inOrder.verify(viewModel, times(1)).initiatePostError(mockedError.exception)
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