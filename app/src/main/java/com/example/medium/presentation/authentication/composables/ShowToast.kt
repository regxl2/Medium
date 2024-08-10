package com.example.medium.presentation.authentication.composables

import android.content.Context
import android.widget.Toast

fun showErrorToast(message: String, context: Context){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}