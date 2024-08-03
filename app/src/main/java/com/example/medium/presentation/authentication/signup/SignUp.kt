package com.example.medium.presentation.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medium.presentation.composables.FormInput

@Composable
fun SignUp(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 64.dp)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(text = "Create an account", fontSize = 32.sp , fontWeight = FontWeight.Bold)
            FormInput()
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
                        // TODO
                    }
                    .padding(8.dp)){
                    Text(
                        text = "Login",
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Sign up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUp() {
    SignUp()
}