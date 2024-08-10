package com.example.medium.data.mappers

import com.example.medium.data.remote.TokenDto
import com.example.medium.domain.model.Token


fun TokenDto.toToken(): Token{
    return Token(jwt = jwt)
}