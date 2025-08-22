package com.example.myapplication.styled_dsl.own.builders.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import com.example.myapplication.styled_dsl.own.annotations.DslStyleText

@DslStyleText
class StyleTextBuilder {

    private val builder = AnnotatedString.Builder()

    var text: String = ""
    var startIndex: Int? = null
    var endIndex: Int? = null
    var decorStyle: SpanStyle? = null
    var paragraphStyle: ParagraphStyle? = null

    fun build(): AnnotatedString = builder.apply {
        append(text)
        val start = startIndex ?: 0
        val end = endIndex ?: length
        paragraphStyle?.let { paragraph -> addStyle(style = paragraph, start = start, end = end) }
        decorStyle?.let { span -> addStyle(style = span, start = start, end = end) }
    }.toAnnotatedString()
}

@DslStyleText
inline fun styleText(block: StyleTextBuilder.() -> Unit): AnnotatedString =
    StyleTextBuilder()
        .apply(block)
        .build()
