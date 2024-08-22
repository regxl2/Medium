package com.example.medium.presentation.blog.blogs.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medium.domain.mappers.htmlToSimpleString
import com.example.medium.domain.model.Post
import com.example.medium.presentation.blog.BlogUiEvent

@Composable
fun Post(modifier: Modifier = Modifier, post: Post, onEvent: (BlogUiEvent)-> Unit) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        onClick = { onEvent(BlogUiEvent.OnClickPost(post = post)) },
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(36.dp)
                        .width(36.dp)
                        .background(color = Color.Gray)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = post.authorName[0].toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(text = post.authorName, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(4.dp)
                        .background(color = Color.Black)
                )
                Text(
                    text = post.createdAt,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = post.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = post.htmlToSimpleString(post.content),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${post.expectedReadTime()} min(s) read",
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun PreviewArticle() {
    Post(
        post = Post(
            id = "rer",
            title = "How to make paneer (Paneer Recipe)",
            content = "Making paneer, a type of fresh cheese common in South Asian cuisine, is relatively simple and requires just a few ingredients. Here’s a step-by-step guide:Ingredients- 1 liter of whole milk (full cream milk is best)- 2-3 tablespoons of lemon juice or white vinegar- A pinch of salt (optional) Equipment",
            authorName = "Kumar",
            createdAt = "24 June 2024"
        ),
        onEvent = {}
    )
}