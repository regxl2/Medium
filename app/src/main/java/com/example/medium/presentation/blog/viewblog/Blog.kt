package com.example.medium.presentation.blog.viewblog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medium.presentation.blog.BlogUiEvent
import com.example.medium.presentation.blog.SharedViewModel

@Composable
fun BlogRoot(navController: NavController, sharedViewModel: SharedViewModel) {
    sharedViewModel.blogUiState.value?.let {
        Blog(state = it){ event, ->
            when(event){
                is BlogUiEvent.OnNavigateBack -> {
                    navController.navigateUp()
                }
                else -> Unit
            }
            sharedViewModel.onEventBlogs(event)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Blog(modifier: Modifier = Modifier, state: BlogUiState, onEvent: (BlogUiEvent)-> Unit) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(BlogUiEvent.OnNavigateBack)
                    }){
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                }
            )
        }
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(text = state.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(
                text = state.content,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(36.dp)
                        .width(36.dp)
                        .background(color = Color.Gray)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = state.authorName[0].toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(text = state.authorName, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBlog() {
    Blog(
        state = BlogUiState(
            title = "How to make paneer (Paneer Recipe)",
            content = AnnotatedString("Making paneer, a type of fresh cheese common in South Asian cuisine, is relatively simple and requires just a few ingredients. Here’s a step-by-step guide:Ingredients- 1 liter of whole milk (full cream milk is best)- 2-3 tablespoons of lemon juice or white vinegar- A pinch of salt (optional) Equipment"),
            authorName = "Kumar",
        ),
        onEvent = {}
    )
}