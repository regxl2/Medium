package com.example.medium.domain.repository

import com.example.medium.domain.model.Post
import com.example.medium.domain.model.PublishBlog
import com.example.medium.domain.model.PublishedBlog
import com.example.medium.domain.util.Resource

interface PostRepository {
    suspend fun postArticle(token: String, publishBlog: PublishBlog): Resource<PublishedBlog>

    suspend fun getArticles(token: String):  Resource<List<Post>>

    suspend fun getArticle(token: String, id: Int): Resource<Post>
}