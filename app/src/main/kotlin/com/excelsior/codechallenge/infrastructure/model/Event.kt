package com.excelsior.codechallenge.infrastructure.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Event(
    val id: String,
    val name: String,
    val ticketPrice: Double = 0.0,
    val ticketPriceString: String = ticketPrice.toString(),
    val formattedDate: String,
    val date: Date?,
    val email: String?,
    val description: String?,
    val phone: String?,
    val address: String?
) : Parcelable