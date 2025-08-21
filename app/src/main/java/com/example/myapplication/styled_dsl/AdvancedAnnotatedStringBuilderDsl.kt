package com.example.myapplication.styled_dsl

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

class AdvancedAnnotatedStringBuilderDsl : AnnotatedStringBuilderDsl() {

    // Методы для быстрого создания стилей
    fun red(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, spanStyleBlock = {
            color = Color.Red
            block()
        })
    }

    fun blue(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, spanStyleBlock = {
            color = Color.Blue
            block()
        })
    }

    fun green(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, spanStyleBlock = {
            color = Color.Green
            block()
        })
    }

    fun centered(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(
            content,
            paragraphStyleBlock = { textAlign = TextAlign.Center },
            spanStyleBlock = { block() }
        )
    }

    fun rightAligned(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(
            content, paragraphStyleBlock = { textAlign = TextAlign.Right },
            spanStyleBlock = { block() }
        )
    }

    fun topAligned(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        // Эмуляция верхнего выравнивания через компактный lineHeight
        styleText(
            content, paragraphStyleBlock = { lineHeight = 14.sp },
            spanStyleBlock = { block() }
        )
    }

    fun withLineHeight(content: String, lineHeight: TextUnit, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(
            content, paragraphStyleBlock = { this.lineHeight = lineHeight },
            spanStyleBlock = { block() }
        )
    }
}

fun buildAdvancedAnnotatedString(block: AdvancedAnnotatedStringBuilderDsl.() -> Unit): AnnotatedString {
    return AdvancedAnnotatedStringBuilderDsl().apply(block).build()
}