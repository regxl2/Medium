package com.example.medium.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormInput(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "Email", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "example@gmail.com",
            onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Password", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "example@123",
            onValueChange = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewForm() {
    FormInput()
}