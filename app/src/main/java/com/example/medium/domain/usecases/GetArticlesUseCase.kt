package com.example.medium.domain.usecases

import com.example.medium.domain.model.Post
import com.example.medium.domain.repository.PostRepository
import com.example.medium.domain.util.Resource
import java.util.concurrent.Flow
import javax.inject.Inject

class GetArticlesUseCase@Inject constructor(private val postRepository: PostRepository){
    suspend operator fun invoke(token: String):Resource<List<Post>>{
        return postRepository.getArticles(token = token)
    }
}