package io.github.artofpaganini.styled_text.builders.text

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import io.github.artofpaganini.styled_text.annotations.DslStyledText
import io.github.artofpaganini.styled_text.builders.container.StyledTextCollector

/**
 * @property [outerDecorStyle] and [outerParagraphStyle] - If startIndex/endIndex are specified and specific
 * decorStyle/paragraphStyle have been applied to the text within those indices,
 * you also have the option to specify the style for the text *outside* the bounds of these indices.
 */
@DslStyledText
class StyleTextBuilder : StyledTextCollector {

    private val builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        builder.append(text)
    }

    var text: String = ""
    var startIndex: Int? = null
    var endIndex: Int? = null
    var inlineContentId: String = ""
    var inlineContentIndex: Int = 0
    var textStyle: TextStyle? = null
    var decorStyle: SpanStyle? = null
    var paragraphStyle: ParagraphStyle? = null

    var outerDecorStyle: SpanStyle? = null
    var outerParagraphStyle: ParagraphStyle? = null

    fun build(): AnnotatedString = builder.apply {
        if (inlineContentId.isNotEmpty()) {
            val index = when {
                inlineContentIndex > text.lastIndex -> text.lastIndex
                inlineContentIndex < 0 -> 0
                else -> inlineContentIndex
            }
            val firstText = text.substring(0, index)
            val secondTxt = text.substring(index, text.lastIndex)
            append(firstText)
            appendInlineContent(inlineContentId)
            append(secondTxt)
            applyStyles()
        } else {
            buildCommonText()
        }
    }.toAnnotatedString()

    private fun AnnotatedString.Builder.buildCommonText() {
        append(text)
        applyStyles()
    }

    private fun AnnotatedString.Builder.applyStyles() {
        val start = startIndex ?: 0
        val end = endIndex ?: length
        paragraphStyle?.let { paragraph -> addStyle(style = paragraph, start = start, end = end) }
        decorStyle?.let { span -> addStyle(style = span, start = start, end = end) }
        textStyle?.let { style ->
            val paragraphStyle = style.toParagraphStyle()
            val spanStyle = style.toSpanStyle()
            addStyle(style = paragraphStyle, start = start, end = end)
            addStyle(style = spanStyle, start = start, end = end)
        }
        if (start > 0) {
            outerDecorStyle?.let { defaultStyle -> addStyle(defaultStyle, 0, start) }
            outerParagraphStyle?.let { defaultStyle -> addStyle(defaultStyle, 0, start) }
        }
        if (end < length) {
            outerDecorStyle?.let { defaultStyle -> addStyle(defaultStyle, end, length) }
            outerParagraphStyle?.let { defaultStyle -> addStyle(defaultStyle, end, length) }
        }
    }
}

@DslStyledText
inline fun styledText(block: StyleTextBuilder.() -> Unit): AnnotatedString =
    StyleTextBuilder()
        .apply(block)
        .build()
