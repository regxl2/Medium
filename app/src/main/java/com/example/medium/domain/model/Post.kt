package com.example.medium.domain.model

data class Post(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String,
    val authorName: String
){
    fun expectedReadTime(): Int{
        return content.length/300
    }
}