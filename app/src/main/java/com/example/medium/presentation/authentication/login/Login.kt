package com.example.medium.presentation.authentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.medium.presentation.Routes
import com.example.medium.presentation.authentication.AuthUiEvent
import com.example.medium.presentation.authentication.AuthUiState
import com.example.medium.presentation.composables.LoadingScreen
import com.example.medium.presentation.authentication.composables.LogInForm
import com.example.medium.presentation.composables.showErrorToast

@Composable
fun LoginRoot(navController: NavController, viewModel: LoginViewModel = hiltViewModel()){
    val state = viewModel.authUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = state.value.onSuccessfulLogIn, key2 = state.value.errorMessage) {
        if(state.value.onSuccessfulLogIn){
            navController.navigate(route = Routes.BlogNav.name){
                popUpTo(route = Routes.AuthNav.name){
                    inclusive = true
                }
            }
            viewModel.onNavigateToBlog()
        }
    }
    Login(state = state.value, afterErrorShown = {viewModel.afterErrorShown()}) { event ->
        when(event){
            is AuthUiEvent.NavigateToSignUp -> {
                navController.navigate(route = Routes.SignUp.name){
                    popUpTo(route = Routes.BlogNav.name){
                        inclusive = true
                    }
                }
            }
            else -> {}
        }
        viewModel.onEvent(event)
    }
}


@Composable
fun Login(modifier: Modifier = Modifier, state: AuthUiState.LogIn, afterErrorShown: ()-> Unit, onEvent: (AuthUiEvent)->Unit) {
    val localContext = LocalContext.current
    Box(modifier = modifier
        .fillMaxSize()
        .padding(top = 64.dp)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(text = "Access the account", fontSize = 32.sp , fontWeight = FontWeight.Bold)
            LogInForm(state = state, onEvent = onEvent)
        }
        if(state.isLoading){
            LoadingScreen()
        }
        LaunchedEffect(key1 = state.errorMessage) {
            state.errorMessage?.let {
                showErrorToast(message = it, context = localContext)
                afterErrorShown()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewSignUp() {
    Login(state = AuthUiState.LogIn(), afterErrorShown = {}, onEvent = {})
}