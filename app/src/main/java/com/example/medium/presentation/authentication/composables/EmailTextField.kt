package com.example.medium.presentation.authentication.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.medium.ui.theme.skyBlue

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (value: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        modifier = modifier
            .onFocusChanged { focusState: FocusState ->
                isFocused = focusState.isFocused
            },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }),
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = if (isFocused) skyBlue else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.padding(16.dp)){
                innerTextField()
            }
        }
    }
}