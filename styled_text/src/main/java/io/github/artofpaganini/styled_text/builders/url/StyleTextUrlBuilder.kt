package io.github.artofpaganini.styled_text.builders.url

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink
import io.github.artofpaganini.styled_text.annotations.DslStyledUrl

@DslStyledUrl
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
        withLink(linkAnnotation) { append(urlTitle) }
    }.toAnnotatedString()
}

@DslStyledUrl
inline fun styledUrl(block: StyleTextUrlBuilder.() -> Unit): AnnotatedString =
    StyleTextUrlBuilder()
        .apply(block)
        .build()
