package io.github.artofpaganini.styled_text.builders.link

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink
import io.github.artofpaganini.styled_text.annotations.DslInlineContent
import io.github.artofpaganini.styled_text.annotations.DslStyledAnnotations
import io.github.artofpaganini.styled_text.builders.container.StyledTextCollector
import io.github.artofpaganini.styled_text.builders.inline_content.InlineContentBuilder

@DslStyledAnnotations
class StyleTextLinkBuilder : StyledTextCollector {

    private val builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        builder.append(text)
    }

    var anchor: String = "link"
    var link: String = ""
    var decorStyle: SpanStyle? = null
    var hoveredStyle: SpanStyle? = null
    var focusedStyle: SpanStyle? = null
    var pressedStyle: SpanStyle? = null

    fun build(): AnnotatedString = builder.apply {
        val linkAnnotation = LinkAnnotation.Clickable(
            tag = link,
            styles = TextLinkStyles(
                style = decorStyle,
                focusedStyle = focusedStyle,
                hoveredStyle = hoveredStyle,
                pressedStyle = pressedStyle
            ),
            linkInteractionListener = null
        )
        withLink(linkAnnotation) { append(anchor) }
    }.toAnnotatedString()

    @DslInlineContent
    inline fun inlineContent(block: @DslStyledAnnotations InlineContentBuilder.() -> Unit): AnnotatedString =
        InlineContentBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)
}

@DslStyledAnnotations
inline fun styledLink(block: StyleTextLinkBuilder.() -> Unit): AnnotatedString =
    StyleTextLinkBuilder()
        .apply(block)
        .build()
