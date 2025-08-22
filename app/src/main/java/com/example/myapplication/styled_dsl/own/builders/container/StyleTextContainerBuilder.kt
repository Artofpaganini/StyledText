package com.example.myapplication.styled_dsl.own.builders.container

import androidx.compose.ui.text.AnnotatedString
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextContainer
import com.example.myapplication.styled_dsl.own.annotations.DslStyleText
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextUrl
import com.example.myapplication.styled_dsl.own.builders.text.StyleTextBuilder
import com.example.myapplication.styled_dsl.own.builders.url.StyleTextUrlBuilder

internal interface CompositeStyleTextContainer {
    fun collect(text: AnnotatedString)
}

@DslStyleTextContainer
class StyleTextContainerBuilder : CompositeStyleTextContainer {

    private val containerBuilder: AnnotatedString.Builder = AnnotatedString.Builder()

    override fun collect(text: AnnotatedString) {
        containerBuilder.append(text)
    }

    fun build(): AnnotatedString = containerBuilder.toAnnotatedString()

    @DslStyleTextUrl
    inline fun styleUrl(block: @DslStyleTextContainer StyleTextUrlBuilder.() -> Unit): AnnotatedString =
        StyleTextUrlBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)

    @DslStyleText
    inline fun styleText(block: @DslStyleTextContainer StyleTextBuilder.() -> Unit): AnnotatedString =
        StyleTextBuilder()
            .apply(block)
            .build()
            .also(block = ::collect)
}

@DslStyleTextContainer
inline fun styleTextContainer(receiver: StyleTextContainerBuilder.() -> Unit): AnnotatedString =
    StyleTextContainerBuilder().apply(receiver).build()
