package com.example.medium.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class TokenDto(
    @SerialName("jwt")
    val jwt: String,
)
