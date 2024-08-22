package com.example.medium.domain.util

fun errorMessage(code: Int): String{
    return when(code){
        401 -> "Unauthorized"
        403 -> "User doesn't exist or incorrect password"
        409 -> "User with this email already exists"
        411 -> "Inputs are incorrect"
        500 -> "Internal server error"
        else -> "Something went wrong"
    }
}