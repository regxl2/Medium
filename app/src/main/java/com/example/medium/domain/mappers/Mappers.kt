package com.example.medium.domain.mappers

import com.example.medium.data.remote.LogInBodyDto
import com.example.medium.data.remote.SignUpBodyDto
import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.SignUpBody

fun SignUpBody.toSignUpBodyDto(): SignUpBodyDto{
    return SignUpBodyDto(name = name, email = email, password =password)
}

fun LogInBody.toLogInBodyDto(): LogInBodyDto{
    return LogInBodyDto(email = email, password = password)
}