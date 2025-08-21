package com.example.myapplication.styled_dsl.own

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles

class StyleTextBuilder {
    private val builder = AnnotatedString.Builder()

    var text: String? = null
    private val spanStyles: List<AnnotatedString.Range<SpanStyle>> = emptyList()
    private val paragraphStyles: List<AnnotatedString.Range<ParagraphStyle>> = emptyList()

    fun build(): AnnotatedString = builder.apply {
        append(text.orEmpty())
    }.toAnnotatedString()
}

private fun AnnotatedString.Builder.applyStyle(
    styledString: StyledString,
    startIndex: Int,
    endIndex: Int,
    onClick: (String) -> Unit
) {
    when (val type = styledString) {
        is StyledString.ClickableUrl -> {
            val linkAnnotation = LinkAnnotation.Url(
                url = styledString.url,
                styles = TextLinkStyles(style = styledString.style),
            )
            addLink(linkAnnotation, startIndex, endIndex)
        }
        is StyledString.ClickableCustom -> {
            val linkAnnotation = LinkAnnotation.Clickable(
                tag = styledString.text,
                styles = TextLinkStyles(style = styledString.style),
                linkInteractionListener = { onClick(type.text) }
            )
            addLink(linkAnnotation, startIndex, endIndex)
        }

        is StyledString.Regular -> {
            addStyle(
                style = styledString.style,
                start = startIndex,
                end = endIndex
            )
        }
    }
}

inline fun styledText(block: StyleTextBuilder.() -> Unit): AnnotatedString = StyleTextBuilder().apply(block).build()
