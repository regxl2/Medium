package com.example.medium.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.medium.presentation.authentication.login.LoginRoot
import com.example.medium.presentation.authentication.signup.SignUpRoot
import com.example.medium.presentation.blog.SharedViewModel
import com.example.medium.presentation.blog.addblog.AddBlogRoot
import com.example.medium.presentation.blog.blogs.BlogsRoot
import com.example.medium.presentation.blog.viewblog.BlogRoot

@Composable
fun NavGraph(navController: NavHostController, sharedViewModel: SharedViewModel = hiltViewModel()){
    NavHost(navController = navController, startDestination = Routes.AuthNav.name){
        navigation(startDestination = Routes.SignUp.name, route = Routes.AuthNav.name){
            composable(route = Routes.SignUp.name){
                SignUpRoot(navController = navController)
            }
            composable(route = Routes.Login.name){
                LoginRoot(navController = navController)
            }
        }

        navigation(startDestination = Routes.Blogs.name, route = Routes.BlogNav.name){
            composable(route = Routes.Blogs.name){
                BlogsRoot(navController = navController, sharedViewModel = sharedViewModel)
            }
            composable(route = Routes.Blog.name){
                BlogRoot(navController = navController, sharedViewModel = sharedViewModel)
            }
            composable(route = Routes.AddBlog.name){
                AddBlogRoot(navController = navController)
            }
        }
    }
}
