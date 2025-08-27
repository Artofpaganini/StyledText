package io.github.artofpaganini.styled_text.builders.url

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink
import io.github.artofpaganini.styled_text.annotations.DslInlineContent
import io.github.artofpaganini.styled_text.annotations.DslStyledUrl
import io.github.artofpaganini.styled_text.builders.container.StyledTextCollector
import io.github.artofpaganini.styled_text.builders.inline_content.InlineContentBuilder

@DslStyledUrl
class StyleTextUrlBuilder : StyledTextCollector {

    private val builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        builder.append(text)
    }

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

    @DslInlineContent
    inline fun inlineContent(block: @DslStyledUrl InlineContentBuilder.() -> Unit): AnnotatedString =
        InlineContentBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)
}

@DslStyledUrl
inline fun styledUrl(block: StyleTextUrlBuilder.() -> Unit): AnnotatedString =
    StyleTextUrlBuilder()
        .apply(block)
        .build()
