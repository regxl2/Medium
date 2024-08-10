package com.example.medium.presentation

sealed class Routes(val name: String){
    data object Login: Routes(name = "Login")
    data object SignUp: Routes(name = "SignUp")
    data object Blog: Routes(name = "Blog")

    //  Graph Routes
    data object AuthNav: Routes(name = "AuthNav")
    data object BlogNav: Routes(name = "BlogNav")
}