package com.example.medium.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Serializable
data class PostsDto(
    @SerialName("posts") val postsDto: List<PostDto>
)

@Serializable
data class PostDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("published") val published: Boolean,
    @SerialName("author") val author: Author
)


@Serializable
data class Author(
    @SerialName("name") val name: String
)