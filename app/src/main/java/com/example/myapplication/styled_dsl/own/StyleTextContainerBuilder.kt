package com.example.myapplication.styled_dsl.own

import androidx.compose.ui.text.AnnotatedString
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextContainer
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextItem

internal interface AnnotatedStringAppender {
    fun connect(annotatedString: AnnotatedString)
}

@DslStyleTextContainer
class StyleTextContainerBuilder : AnnotatedStringAppender {

    private val containerBuilder: AnnotatedString.Builder = AnnotatedString.Builder()

    fun build(): AnnotatedString = containerBuilder.toAnnotatedString()

    override fun connect(annotatedString: AnnotatedString) {
        containerBuilder.append(annotatedString)
    }

    @DslStyleTextItem
    inline fun StyleTextContainerBuilder.styleTextItem(receiver: @DslStyleTextContainer StyleTextItemBuilder.() -> Unit) {
        StyleTextItemBuilder()
            .apply(block = receiver)
            .build()
            .also(block = ::connect)
    }
}

@DslStyleTextContainer
inline fun styleTextContainer(receiver: StyleTextContainerBuilder.() -> Unit): AnnotatedString =
    StyleTextContainerBuilder().apply(receiver).build()
