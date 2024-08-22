package com.example.medium.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PublishBlogDto (
    @SerialName("title") val title : String,
    @SerialName("content") val content: String,
    @SerialName("authorId") val authorId: String
)