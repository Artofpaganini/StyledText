package io.github.artofpaganini.styled_text.builders.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import io.github.artofpaganini.styled_text.annotations.DslStyledText

@DslStyledText
class StyleTextBuilder {

    private val builder = AnnotatedString.Builder()

    var text: String = ""
    var startIndex: Int? = null
    var endIndex: Int? = null
    var decorStyle: SpanStyle? = null
    var paragraphStyle: ParagraphStyle? = null

    var outerDecorStyle: SpanStyle? = null
    var outerParagraphStyle: ParagraphStyle? = null

    fun build(): AnnotatedString = builder.apply {
        append(text)
        val start = startIndex ?: 0
        val end = endIndex ?: length
        paragraphStyle?.let { paragraph -> addStyle(style = paragraph, start = start, end = end) }
        decorStyle?.let { span -> addStyle(style = span, start = start, end = end) }
        if (start > 0) {
            outerDecorStyle?.let { defaultStyle -> addStyle(defaultStyle, 0, start) }
            outerParagraphStyle?.let { defaultStyle -> addStyle(defaultStyle, 0, start) }
        }
        if (end < length) {
            outerDecorStyle?.let { defaultStyle -> addStyle(defaultStyle, end, length) }
            outerParagraphStyle?.let { defaultStyle -> addStyle(defaultStyle, end, length) }
        }
    }.toAnnotatedString()
}

@DslStyledText
inline fun styledText(block: StyleTextBuilder.() -> Unit): AnnotatedString =
    StyleTextBuilder()
        .apply(block)
        .build()
