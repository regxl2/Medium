package com.example.medium.presentation.blog.addblog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.medium.presentation.composables.LoadingScreen
import com.example.medium.presentation.composables.showErrorToast
import com.example.medium.ui.theme.skyBlue


@Composable
fun AddBlogRoot(navController: NavController, viewModel: AddBlogViewModel = hiltViewModel()) {
    val state = viewModel.addBlogUiState.collectAsStateWithLifecycle()
    AddBlog(state = state.value) { event ->
        when (event) {
            is AddBlogUiEvent.OnNavigateBack -> {
                navController.navigateUp()
            }
            else -> Unit
        }
        viewModel.onEvent(event)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBlog(
    modifier: Modifier = Modifier,
    state: AddBlogUiState,
    onEvent: (AddBlogUiEvent) -> Unit
) {
    val insets = WindowInsets.ime
    val density = LocalDensity.current
    val imeBottomPadding = with(density) {
        insets.getBottom(density).toDp()
    }
    val localContext = LocalContext.current
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Blog") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(AddBlogUiEvent.OnNavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LaunchedEffect(key1 = state.errorMessage) {
                state.errorMessage?.let {
                    showErrorToast(message = it, context = localContext)
                    onEvent(AddBlogUiEvent.OnError)
                }
            }
            LaunchedEffect(key1 = state.postId) {
                state.postId?.let {
                    onEvent(AddBlogUiEvent.OnNavigateBack)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = imeBottomPadding)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                val titleFocusRequester = remember { FocusRequester() }
                val contentFocusRequester = remember { FocusRequester() }
                val buttonFocusRequester = remember { FocusRequester() }
                var isTitleFocused by remember { mutableStateOf(false) }
                var isContentFocused by remember { mutableStateOf(false) }
                val keyBoardController = LocalSoftwareKeyboardController.current
                Text(text = "Title", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(titleFocusRequester)
                        .onFocusChanged { focusState: FocusState ->
                            if (isTitleFocused) {
                                isContentFocused = false
                            }
                            isTitleFocused = focusState.isFocused
                        },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            contentFocusRequester.requestFocus()
                        }),
                    value = state.title,
                    onValueChange = { onEvent(AddBlogUiEvent.OnTitleChange(title = it)) },
                ) { innerTextField ->
                    Row(
                        modifier = Modifier
                            .border(
                                width = 4.dp,
                                color = if (isTitleFocused) skyBlue else Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier.padding(16.dp)) {
                            innerTextField()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Content", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(contentFocusRequester)
                        .onFocusChanged { focusState: FocusState ->
                            if (isTitleFocused) {
                                isTitleFocused = false
                            }
                            isContentFocused = focusState.isFocused
                        },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyBoardController?.hide()
                        }),
                    value = state.content,
                    onValueChange = { onEvent(AddBlogUiEvent.OnContentChange(content = it)) },
                ) { innerTextField ->
                    Row(
                        modifier = Modifier
                            .border(
                                width = 4.dp,
                                color = if (isContentFocused) skyBlue else Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier.padding(16.dp)) {
                            innerTextField()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
                            .focusRequester(buttonFocusRequester)
                            .align(Alignment.Center),
                        onClick = {
                            keyBoardController?.hide()
                            onEvent(AddBlogUiEvent.OnClickPublish)
                        },
                        colors = ButtonDefaults.buttonColors()
                            .copy(contentColor = Color.Black, containerColor = Color.Green)
                    ) {
                        Text(text = "Publish")
                    }
                }
            }
            if(state.isLoading){
                LoadingScreen()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAddBlog() {
    AddBlog(
        state = AddBlogUiState(title = "title", content = "content"),
        onEvent = {}
    )
}