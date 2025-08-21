package com.example.myapplication.styled_dsl.own

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle

class StyleTextContainer {

    val content: MutableList<AnnotatedString> = mutableListOf()

    inline fun styleContainer(receiver: StyleTextBuilder.() -> Unit) {
        StyleTextBuilder()
            .apply(receiver)
            .build()
            .also { spannableElement ->
                content.add(spannableElement)
            }
    }
}

inline fun styleBlock(receiver: StyleTextContainer.() -> Unit) = StyleTextContainer().apply(receiver)

fun StyleTextContainer.element(
    part: StyleTextPart,
    spanStyle: SpanStyle? = null,
    paragraphStyle: ParagraphStyle? = null
) = styleContainer {
    this.part = part
    this.spanStyle = spanStyle
    this.paragraphStyle = paragraphStyle
}