package com.example.myapplication.styled_dsl.own

import androidx.compose.runtime.Immutable
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextItem
import com.example.myapplication.styled_dsl.own.annotations.DslTextRegular
import com.example.myapplication.styled_dsl.own.annotations.DslTextUrl

@Immutable
sealed interface StyleTextType {

    val text: String

    data class Url(
        val tag: String,
        override val text: String
    ) : StyleTextType

    data class Regular(override val text: String) : StyleTextType
}

@DslTextUrl
class UrlItemBuilder {
    var tag: String = ""
    var text: String = ""

    fun build(): StyleTextType.Url = StyleTextType.Url(tag = tag, text = text)
}

@DslTextUrl
inline fun url(block: @DslStyleTextItem UrlItemBuilder.() -> Unit): StyleTextType.Url =
    UrlItemBuilder().apply(block).build()

@DslTextRegular
class RegularItemBuilder {
    var text: String = ""

    fun build(): StyleTextType.Regular = StyleTextType.Regular(text)
}

@DslTextRegular
inline fun regular(block: @DslStyleTextItem RegularItemBuilder.() -> Unit): StyleTextType.Regular =
    RegularItemBuilder().apply(block).build()