package ru.takeshiko.hungrypeople.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactMessage(
    val id: Int? = null,
    val name: String,
    val email: String,
    val phone: String,
    val message: String
)