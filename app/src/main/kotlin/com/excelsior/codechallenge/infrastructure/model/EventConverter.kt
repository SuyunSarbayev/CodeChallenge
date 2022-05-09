package com.excelsior.codechallenge.infrastructure.model

import com.excelsior.codechallenge.infrastructure.utils.FormatUtils

class EventConverter(var formatUtils: FormatUtils) {

    fun fromSource(source: Map<String, Any?>): Event =
        Event(
            id = (source["guid"] as? String).orEmpty(),
            name = (source["event"] as? String).orEmpty(),
            formattedDate = formatUtils.formatDateToLocaleDate((source["date"] as? String).orEmpty()),
            ticketPrice = (source["ticketPrice"] as Double),
            date = formatUtils.getDateFromString((source["date"] as? String).orEmpty()),
            email = (source["email"] as? String).orEmpty(),
            phone = (source["phone"] as? String).orEmpty(),
            description = (source["description"] as? String).orEmpty(),
            address = (source["address"] as? String).orEmpty()
        )
}