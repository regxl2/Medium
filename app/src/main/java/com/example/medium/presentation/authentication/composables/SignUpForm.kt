package com.example.medium.presentation.authentication.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medium.presentation.authentication.AuthUiEvent
import com.example.medium.presentation.authentication.AuthUiState

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    state: AuthUiState.SignUp,
    onEvent: (AuthUiEvent) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        var isNameFocused by remember { mutableStateOf(false) }
        var isEmailFocused by remember { mutableStateOf(false) }
        var isPasswordFocused by remember { mutableStateOf(false) }
        val nameFocusRequester = remember { FocusRequester() }
        val emailFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }
        val keyBoardController = LocalSoftwareKeyboardController.current

        Text(text = "Name", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        NameTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            isNameFocused = isNameFocused,
            nameFocusRequester = nameFocusRequester,
            emailFocusRequester = emailFocusRequester,
            onFocusChanged = { focusState: FocusState ->
                if(focusState.isFocused){
                    isPasswordFocused = false
                    isEmailFocused = false
                }
                isNameFocused = focusState.isFocused
            },
            onValueChange = {
                onEvent(AuthUiEvent.OnNameChange(it.trim()))
            })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            isEmailFocused = isEmailFocused,
            emailFocusRequester = emailFocusRequester,
            passwordFocusRequester = passwordFocusRequester,
            onFocusChanged = { focusState: FocusState ->
                if(focusState.isFocused){
                    isPasswordFocused = false
                    isNameFocused = false
                }
                isEmailFocused = focusState.isFocused
            },
            onValueChange = {
                onEvent(AuthUiEvent.OnEmailChange(it.trim()))
            })
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Password", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            isPasswordFocused = isPasswordFocused,
            passwordFocusRequester = passwordFocusRequester,
            onFocusChanged = { focusState: FocusState ->
                if(focusState.isFocused){
                    isEmailFocused = false
                    isNameFocused = false
                }
                isPasswordFocused = focusState.isFocused
            },
            onValueChange = {
                onEvent(AuthUiEvent.OnPasswordChange(it.trim()))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an account?", fontWeight = FontWeight.SemiBold)
            Box(modifier = Modifier
                .clickable {
                    onEvent(AuthUiEvent.NavigateToLogin)
                }
                .padding(8.dp)){
                Text(
                    text = "Login",
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.Black,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Gray
            ),
            onClick = {
                onEvent(AuthUiEvent.OnSignUp)
                keyBoardController?.hide()
            }) {
            Text(text = "Sign up")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewForm() {
    SignUpForm(state = AuthUiState.SignUp(), onEvent = {})
}