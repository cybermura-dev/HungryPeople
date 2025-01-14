package ru.takeshiko.hungrypeople.models

import kotlinx.serialization.Serializable

/**
 * Data class representing a reservation.
 * This class is used to store the details of a reservation.
 *
 * @property name The name of the person making the reservation.
 * @property email The email address of the person making the reservation (optional).
 * @property phone The phone number of the person making the reservation (optional).
 * @property people The number of people for the reservation.
 * @property date The date of the reservation in the format "yyyy-MM-dd".
 * @property time The time of the reservation in the format "HH:mm".
 */
@Serializable
data class Reservation(
    val name: String,
    val email: String?,
    val phone: String?,
    val people: Int,
    val date: String,
    val time: String
)
