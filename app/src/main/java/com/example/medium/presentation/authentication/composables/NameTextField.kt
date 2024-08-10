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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.medium.ui.theme.skyBlue

@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    value: String,
    isNameFocused: Boolean,
    nameFocusRequester: FocusRequester,
    emailFocusRequester: FocusRequester,
    onFocusChanged: (focusState: FocusState)-> Unit,
    onValueChange: (value: String) -> Unit
) {
    BasicTextField(
        modifier = modifier.
        focusRequester(nameFocusRequester)
            .onFocusChanged { focusState: FocusState ->
                onFocusChanged(focusState)
            },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                emailFocusRequester.requestFocus()
            }),
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = if (isNameFocused) skyBlue else Color.Gray,
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