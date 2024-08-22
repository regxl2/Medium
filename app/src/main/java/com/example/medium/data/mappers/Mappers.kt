package com.example.medium.data.mappers

import com.example.medium.data.dto.PostDto
import com.example.medium.data.dto.PublishedBlogDto
import com.example.medium.data.dto.TokenDto
import com.example.medium.domain.model.Post
import com.example.medium.domain.model.PublishedBlog
import com.example.medium.domain.model.Token
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun TokenDto.toToken(): Token{
    return Token(jwt = jwt)
}

fun PostDto.toPost(): Post{
    return Post(
        id = id,
        title = title,
        content = content,
        createdAt = formatDate(createdAt),
        authorName = author.name
    )
}

fun PostDto.formatDate(input: String):String{
    val zonedDateTime = ZonedDateTime.parse(input)
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return zonedDateTime.format(outputFormatter)
}


fun PublishedBlogDto.toPublishBlog(): PublishedBlog {
    return PublishedBlog(postId = postId)
}