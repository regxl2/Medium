package com.example.medium.domain.mappers

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.core.text.HtmlCompat
import com.example.medium.data.dto.LogInBodyDto
import com.example.medium.data.dto.PublishBlogDto
import com.example.medium.data.dto.SignUpBodyDto
import com.example.medium.domain.model.LogInBody
import com.example.medium.domain.model.Post
import com.example.medium.domain.model.PublishBlog
import com.example.medium.domain.model.SignUpBody
import com.example.medium.presentation.blog.viewblog.BlogUiState

fun SignUpBody.toSignUpBodyDto(): SignUpBodyDto {
    return SignUpBodyDto(name = name, email = email, password =password)
}

fun LogInBody.toLogInBodyDto(): LogInBodyDto {
    return LogInBodyDto(email = email, password = password)
}

fun PublishBlog.toPublishBlogDto(): PublishBlogDto {
    return PublishBlogDto(title = title, content = content, authorId = authorId)
}

fun Post.htmlToSimpleString(content: String): String{
    val regex = Regex("<img[^>]*>", RegexOption.IGNORE_CASE)
    val newContent =  content.replace(regex, "")
    val spannedText =  HtmlCompat.fromHtml(newContent,
        HtmlCompat.FROM_HTML_MODE_COMPACT
    ).toString()
    val paragraph = mutableListOf<String>()
    spannedText.lines().forEach { line ->
        if(line.isNotBlank()){
            paragraph.add(line.trim())
        }
    }
    return paragraph.joinToString(" ")
}

fun Post.toBlogUiState(): BlogUiState{
    val regex = Regex("<img[^>]*>", RegexOption.IGNORE_CASE)
    val newContent =  content.replace(regex, "")
    val spanned: Spanned = HtmlCompat.fromHtml(newContent, HtmlCompat.FROM_HTML_MODE_LEGACY)
    val annotatedString = buildAnnotatedString {
        val spannable = spanned as? Spanned
        spannable?.let {
            for (i in spannable.indices) {
                val spanStyles = spannable.getSpans(i, i + 1, Any::class.java)
                val text = spannable.subSequence(i, i + 1)
                var currentStyle = SpanStyle()
                spanStyles.forEach { style ->
                    when (style) {
                        is StyleSpan -> {
                            currentStyle = when (style.style) {
                                Typeface.BOLD -> currentStyle.copy(fontWeight = FontWeight.Bold)
                                Typeface.ITALIC -> currentStyle.copy(fontStyle = FontStyle.Italic)
                                else -> currentStyle
                            }
                        }
                        is ForegroundColorSpan -> {
                            currentStyle = currentStyle.copy(color = Color(style.foregroundColor))
                        }
                    }
                }
                withStyle(style = currentStyle) {
                    append(text)
                }
            }
        }
    }
    return BlogUiState(title = title, content = annotatedString, authorName = authorName)
}