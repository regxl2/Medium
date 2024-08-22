package com.example.medium.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublishedBlogDto(
    @SerialName("id") val postId: String
)