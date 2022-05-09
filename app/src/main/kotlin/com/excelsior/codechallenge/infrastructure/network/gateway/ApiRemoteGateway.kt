package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.ApiService

class ApiRemoteGateway(val apiService: ApiService) : ApiGateway {

    override suspend fun getEvents(): List<Map<String, Any?>> =
        apiService.get().getEvents()

    override suspend fun getEventDetails(eventId: String): Map<String, Any?> =
        apiService.get().getEventDetails(eventId)
}