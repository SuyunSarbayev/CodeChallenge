package com.excelsior.codechallenge.infrastructure.network.gateway

interface ApiGateway {

    suspend fun getEvents(): List<Map<String, Any?>>

    suspend fun getEventDetails(eventId: String): Map<String, Any?>
}