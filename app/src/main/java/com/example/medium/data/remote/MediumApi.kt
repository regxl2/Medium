package com.example.medium.data.remote

import com.example.medium.data.dto.LogInBodyDto
import com.example.medium.data.dto.PostDto
import com.example.medium.data.dto.PostsDto
import com.example.medium.data.dto.PublishBlogDto
import com.example.medium.data.dto.PublishedBlogDto
import com.example.medium.data.dto.SignUpBodyDto
import com.example.medium.data.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface MediumApi {
    @POST("user/signup")
    suspend fun signUp(@Body body: SignUpBodyDto): Response<TokenDto>

    @POST("user/signin")
    suspend fun logIn(@Body body: LogInBodyDto): Response<TokenDto>

    @POST("blog")
    suspend fun postArticle( @Header("authorization") token: String, @Body body: PublishBlogDto): Response<PublishedBlogDto>

    @GET("blog/bulk")
    suspend fun getArticles(@Header("authorization") token: String): Response<PostsDto>

    @GET("blog/{id}")
    suspend fun getArticle(@Header ("authorization") token: String, @Path("id") id: Int): Response<PostDto>
}