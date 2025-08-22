package com.example.myapplication.styled_dsl.own.builders.url

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextContainer
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextUrl

@DslStyleTextUrl
class StyleTextUrlBuilder {
    private val builder = AnnotatedString.Builder()

    var urlTitle: String = "link"
    var url: String = ""
    var decorStyle: SpanStyle? = null

    var hoveredStyle: SpanStyle? = null

    var focusedStyle: SpanStyle? = null
    var pressedStyle: SpanStyle? = null

    fun build(): AnnotatedString = builder.apply {
        val linkAnnotation = LinkAnnotation.Url(
            url = url,
            styles = TextLinkStyles(
                style = decorStyle,
                focusedStyle = focusedStyle,
                hoveredStyle = hoveredStyle,
                pressedStyle = pressedStyle
            ),
        )
        addLink(url = linkAnnotation, start = 0, end = length)
        withLink(linkAnnotation) { append(urlTitle) }
    }.toAnnotatedString()
}

@DslStyleTextUrl
inline fun styleUrl(block: StyleTextUrlBuilder.() -> Unit): AnnotatedString =
    StyleTextUrlBuilder()
        .apply(block)
        .build()
