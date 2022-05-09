package com.excelsior.codechallenge.eventsOverview.repository

import com.excelsior.codechallenge.eventsOverview.utils.EventsOverviewSorting
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.model.EventConverter
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.network.response.ApiResponse
import retrofit2.HttpException
import java.io.IOException

class EventsRepository(
    val eventsOverviewSorting: EventsOverviewSorting,
    val eventConverter: EventConverter,
    val apiGateway: ApiGateway
) {
    suspend fun initiateRequestEvents(): ApiResponse<List<Event>> =
        try {
            ApiResponse.Success(
                eventsOverviewSorting
                    .initiateDateSortingAscending(apiGateway.getEvents().map {
                        eventConverter.fromSource(it)
                    })
            )
        } catch (e: HttpException) {
            ApiResponse.Error(e)
        } catch (e: IOException) {
            ApiResponse.Error(e)
        }

    suspend fun initiateRequestEventDetail(eventId: String): ApiResponse<Event> =
        try {
            ApiResponse.Success(eventConverter.fromSource(apiGateway.getEventDetails(eventId)))
        } catch (e: HttpException) {
            ApiResponse.Error(e)
        } catch (e: IOException) {
            ApiResponse.Error(e)
        }
}