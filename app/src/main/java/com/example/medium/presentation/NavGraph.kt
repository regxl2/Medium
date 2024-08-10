package com.example.medium.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.medium.presentation.authentication.login.LoginRoot
import com.example.medium.presentation.authentication.signup.SignUpRoot
import com.example.medium.presentation.blog.Blog

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.AuthNav.name){
        navigation(startDestination = Routes.SignUp.name, route = Routes.AuthNav.name){
            composable(route = Routes.SignUp.name){
                SignUpRoot(navController = navController)
            }
            composable(route = Routes.Login.name){
                LoginRoot(navController = navController)
            }
        }

        navigation(startDestination = Routes.Blog.name, route = Routes.BlogNav.name){
            composable(route = Routes.Blog.name){
                Blog()
            }
        }
    }
}
