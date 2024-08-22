package com.example.medium.domain.usecases

import com.auth0.android.jwt.JWT
import com.example.medium.domain.model.PublishBlog
import com.example.medium.domain.model.PublishedBlog
import com.example.medium.domain.repository.PostRepository
import com.example.medium.domain.repository.TokenRepository
import com.example.medium.domain.util.Resource
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class PostArticleUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(title: String, content: String): Resource<PublishedBlog> {
        val token = tokenRepository.getTokenFlow().firstOrNull()
            ?: return Resource.Error("Token not found")

        val jwt = JWT(token)
        val authorId = jwt.getClaim("id").asString()
            ?: return Resource.Error("Invalid token, author ID not found")

        return postRepository.postArticle(
            token = "Bearer $token",
            publishBlog = PublishBlog(
                title = title,
                content = content,
                authorId = authorId
            )
        )
    }
}