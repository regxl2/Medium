package com.example.medium.presentation.blog.blogs.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogTopBar(modifier: Modifier = Modifier, userNameInitial: String, onClickNavigationIcon: ()-> Unit ) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = "Medium", fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = onClickNavigationIcon) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .height(48.dp)
                        .width(48.dp)
                        .background(color = Color.Gray)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = userNameInitial,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewBlogTopBar() {
    BlogTopBar(userNameInitial = "A", onClickNavigationIcon =  {})
}