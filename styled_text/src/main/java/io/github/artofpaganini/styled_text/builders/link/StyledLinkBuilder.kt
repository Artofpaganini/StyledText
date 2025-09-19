package io.github.artofpaganini.styled_text.builders.link

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink
import io.github.artofpaganini.styled_text.annotations.DslInlineContent
import io.github.artofpaganini.styled_text.annotations.DslStyledLink
import io.github.artofpaganini.styled_text.builders.container.StyledTextCollector
import io.github.artofpaganini.styled_text.builders.inline_content.InlineContentBuilder

@DslStyledLink
class StyledLinkBuilder : StyledTextCollector {

    private val builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        builder.append(text)
    }

    var anchor: String = "link"
    var link: String = ""
    var inlineContentId: String = ""
    var inlineContentPos: InlineContentPosition = InlineContentPosition.END
    var decorStyle: SpanStyle? = null
    var hoveredStyle: SpanStyle? = null
    var focusedStyle: SpanStyle? = null
    var pressedStyle: SpanStyle? = null

    fun build(): AnnotatedString = builder.apply {
        if (inlineContentId.isNotEmpty()) {
            appendLinkWithInlineContent()
        } else {
            appendLinkWithStyles()
        }
    }.toAnnotatedString()

    private fun AnnotatedString.Builder.appendLinkWithInlineContent() {
        when (inlineContentPos) {
            InlineContentPosition.START -> {
                appendInlineContent(inlineContentId)
                appendLinkWithStyles()
            }
            InlineContentPosition.END -> {
                appendLinkWithStyles()
                appendInlineContent(inlineContentId)
            }
        }
    }

    private fun AnnotatedString.Builder.appendLinkWithStyles() {
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
    }

    @DslInlineContent
    inline fun inlineContent(block: @DslStyledLink InlineContentBuilder.() -> Unit): AnnotatedString =
        InlineContentBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)

    enum class InlineContentPosition {
        START, END
    }
}

@DslStyledLink
inline fun styledLink(block: StyledLinkBuilder.() -> Unit): AnnotatedString =
    StyledLinkBuilder()
        .apply(block)
        .build()
