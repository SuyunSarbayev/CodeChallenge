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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*
import kotlin.test.assertEquals

class EventsOverviewAndroidViewModelTest {

    @Mock
    lateinit var viewModel: EventsOverviewAndroidViewModel

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
            eventsRepository)
    }

    @Test
    fun initiateRequestEventsViewModelTest(){
        var mockedList = ApiResponse.Success(initiateCreateTestEvents())

        ioScope.launch {
            viewModel.initiateRequestEvents()
            assertEquals(viewModel.loader.value, true)

            Mockito.doReturn(mockedList).`when`(eventsRepository).initiateRequestEvents()
            eventsRepository.initiateRequestEvents()

            val inOrder = Mockito.inOrder(viewModel)
            inOrder.verify(viewModel, Mockito.times(1)).initiateUpdateLiveData(mockedList.data)
            inOrder.verify(viewModel, Mockito.times(1)).initiateUpdateTitle(mockedList.data)

            assertEquals(viewModel.events, mockedList.data)
            assertEquals(viewModel.loader.value, false)
        }
    }

    private fun createCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
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