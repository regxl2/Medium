package com.example.medium.presentation.blog.blogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.medium.presentation.Routes
import com.example.medium.presentation.blog.BlogUiEvent
import com.example.medium.presentation.blog.SharedViewModel
import com.example.medium.presentation.blog.blogs.composables.BlogTopBar
import com.example.medium.presentation.blog.blogs.composables.Post
import com.example.medium.presentation.composables.LoadingScreen
import com.example.medium.presentation.composables.showErrorToast
import kotlinx.coroutines.launch


@Composable
fun BlogsRoot(navController: NavController, viewModel: BlogsViewModel = hiltViewModel(), sharedViewModel: SharedViewModel) {
    val state = viewModel.blogsUiState.collectAsStateWithLifecycle()
    Blogs(state = state.value) {
        event ->
        when(event){
            is BlogUiEvent.Logout -> {
                navController.navigate(route = Routes.AuthNav.name){
                    popUpTo(route = Routes.BlogNav.name){
                        inclusive = true
                    }
                }
            }
            is BlogUiEvent.OnClickPost -> {
                sharedViewModel.onEventBlogs(event)
                navController.navigate(route = Routes.Blog.name)
            }
            is BlogUiEvent.OnClickFloating ->{
                navController.navigate(route = Routes.AddBlog.name)
            }
            else -> Unit
        }
        viewModel.onEvent(event)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Blogs(modifier: Modifier = Modifier, state: BlogsUiState, onEvent: (BlogUiEvent)-> Unit) {
    val localContext = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.6f),
                drawerShape = RectangleShape
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .height(64.dp)
                            .width(64.dp)
                            .background(color = Color.Gray)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = state.user[0].toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = state.user,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider()
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(BlogUiEvent.Logout)
                        },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color.Transparent,
                            contentColor = Color.Gray,
                        )
                    ) {
                        Text(
                            text = "Logout",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }) {
        Scaffold(modifier = modifier.fillMaxSize(),
            topBar = {
                BlogTopBar(userNameInitial = state.user[0].toString()) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            },
            floatingActionButton = {
                Button(
                    onClick = { onEvent(BlogUiEvent.OnClickFloating) },
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = Color.Green, contentColor = Color.Black)
                ) {
                    Text(text = "New")
                }
            }
        )
        { paddingValues ->
            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = { onEvent(BlogUiEvent.LoadPosts) }
            ){
                LaunchedEffect(key1 = state.errorMessage) {
                    state.errorMessage?.let {
                        showErrorToast(it, localContext)
                        onEvent(BlogUiEvent.OnError)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(color = Color.LightGray.copy(0.5f))
                        .padding(vertical = 4.dp)
                ) {
                    val posts = state.posts
                    items(count = posts.size, key = {
                        posts[it].id
                    }) { index ->
                        Post(post = posts[index], onEvent = onEvent)
                    }
                }
                if (state.isLoading) {
                    LoadingScreen()
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBlog() {
    Blogs(state = BlogsUiState(), onEvent = {})
}