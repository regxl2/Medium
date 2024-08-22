package com.example.medium.data.repository

import com.example.medium.data.mappers.toPost
import com.example.medium.data.remote.MediumApi
import com.example.medium.domain.mappers.toPublishBlogDto
import com.example.medium.domain.model.Post
import com.example.medium.domain.model.PublishBlog
import com.example.medium.domain.model.PublishedBlog
import com.example.medium.domain.repository.PostRepository
import com.example.medium.domain.util.Resource
import com.example.medium.domain.util.errorMessage
import javax.inject.Inject

class PostRepositoryImpl@Inject constructor(private val mediumApi: MediumApi): PostRepository {
    override suspend fun postArticle(token: String, publishBlog: PublishBlog): Resource<PublishedBlog> {
        val response = mediumApi.postArticle(token = token, body = publishBlog.toPublishBlogDto())
        return if(response.isSuccessful){
            Resource.Success(data = response.body()?.postId?.let { PublishedBlog(postId = it) })
        }
        else{
            Resource.Error(message = errorMessage(response.code()))
        }
    }

    override suspend fun getArticles(token: String): Resource<List<Post>> {
        val response = mediumApi.getArticles(token = token)
        return if(response.isSuccessful){
            Resource.Success(
                data = response.body()?.postsDto?.map {
                    postDto -> postDto.toPost()
                }
            )
        }
        else{
            Resource.Error(message = errorMessage(response.code()))
        }
    }

    override suspend fun getArticle(token: String, id: Int): Resource<Post> {
        val response = mediumApi.getArticle(token = token, id = id)
        return if(response.isSuccessful){
            Resource.Success(data = response.body()?.toPost())
        }
        else{
            Resource.Error(message = errorMessage(response.code()))
        }
    }

}