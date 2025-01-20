package ru.takeshiko.hungrypeople.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null,
    @SerialName("category_id") val categoryId: Int,
    val title: String,
    val price: Float,
    val description: String
)