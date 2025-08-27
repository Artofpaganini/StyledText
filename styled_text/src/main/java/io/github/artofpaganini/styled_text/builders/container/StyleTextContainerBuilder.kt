package io.github.artofpaganini.styled_text.builders.container

import androidx.compose.ui.text.AnnotatedString
import io.github.artofpaganini.styled_text.annotations.DslInlineContent
import io.github.artofpaganini.styled_text.annotations.DslStyledText
import io.github.artofpaganini.styled_text.annotations.DslStyledTextContainer
import io.github.artofpaganini.styled_text.annotations.DslStyledUrl
import io.github.artofpaganini.styled_text.builders.inline_content.InlineContentBuilder
import io.github.artofpaganini.styled_text.builders.text.StyleTextBuilder
import io.github.artofpaganini.styled_text.builders.url.StyleTextUrlBuilder

internal interface StyledTextCollector {
    fun collect(text: AnnotatedString)
}

@DslStyledTextContainer
class StyleTextContainerBuilder : StyledTextCollector {

    private val containerBuilder: AnnotatedString.Builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        containerBuilder.append(text)
    }

    fun build(): AnnotatedString = containerBuilder.toAnnotatedString()

    @DslStyledUrl
    inline fun styledUrl(block: @DslStyledTextContainer StyleTextUrlBuilder.() -> Unit): AnnotatedString =
        StyleTextUrlBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)

    @DslStyledText
    inline fun styledText(block: @DslStyledTextContainer StyleTextBuilder.() -> Unit): AnnotatedString =
        StyleTextBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)

    @DslInlineContent
    inline fun inlineContent(block: @DslStyledTextContainer InlineContentBuilder.() -> Unit): AnnotatedString =
        InlineContentBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)
}

@DslStyledTextContainer
inline fun styledTextContainer(receiver: StyleTextContainerBuilder.() -> Unit): AnnotatedString =
    StyleTextContainerBuilder().apply(receiver).build()
