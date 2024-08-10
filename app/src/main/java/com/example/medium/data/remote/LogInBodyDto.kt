package com.example.medium.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogInBodyDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)