package io.github.artofpaganini.styled_text.builders.inline_content

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import io.github.artofpaganini.styled_text.annotations.DslInlineContent

@DslInlineContent
class InlineContentBuilder {

    var id: String = ""

    fun build(): AnnotatedString = buildAnnotatedString {
        appendInlineContent(id)
    }
}

@DslInlineContent
inline fun inlineContent(block: InlineContentBuilder.() -> Unit): AnnotatedString =
    InlineContentBuilder()
        .apply(block)
        .build()
