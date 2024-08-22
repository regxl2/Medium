package com.example.medium.data.repository

import com.example.medium.data.mappers.toToken
import com.example.medium.data.remote.MediumApi
import com.example.medium.domain.mappers.toLogInBodyDto
import com.example.medium.domain.mappers.toSignUpBodyDto
import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.SignUpBody
import com.example.medium.domain.model.Token
import com.example.medium.domain.repository.AuthRepository
import com.example.medium.domain.util.Resource
import com.example.medium.domain.util.errorMessage
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val mediumApi: MediumApi) : AuthRepository {

    override suspend fun signUp(body: SignUpBody): Resource<Token> {
        val signUpBodyDto = body.toSignUpBodyDto()
        val response = mediumApi.signUp(body = signUpBodyDto)
        val data = if(response.isSuccessful){
            Resource.Success(data = response.body()?.toToken())
        }
        else{
            Resource.Error(message = errorMessage(response.code()))
        }
        return data
    }

    override suspend fun logIn(body: LogInBody): Resource<Token> {
        val logInBodyDto = body.toLogInBodyDto()
        val response = mediumApi.logIn(body = logInBodyDto)
        val data = if(response.isSuccessful){
            Resource.Success(data = response.body()?.toToken())
        }
        else{
            Resource.Error(message = errorMessage(response.code()))
        }
        return data
    }
}



