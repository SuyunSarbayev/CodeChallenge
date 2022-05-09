package com.excelsior.codechallenge.infrastructure.network

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("events")
    suspend fun getEvents(): List<Map<String, Any?>>

    @GET("event/{event_id}")
    suspend fun getEventDetails(@Path("event_id") eventId: String): Map<String, Any?>
}