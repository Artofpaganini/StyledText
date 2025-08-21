package com.example.myapplication.styled_dsl

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit

open class AnnotatedStringBuilderDsl {
    private val builder = AnnotatedString.Builder()
    private var currentUrl: String? = null

    fun build(): AnnotatedString = builder.toAnnotatedString()

    // Основной метод для стилизованного текста
    fun styleText(
        content: String,
        spanStyleBlock: SpanStyleDsl.() -> Unit = {},
        paragraphStyleBlock: ParagraphStyleDsl.() -> Unit = {},
        url: String? = null
    ) {
        val spanStyle = SpanStyleDsl().apply(spanStyleBlock).build()
        val paragraphStyle = ParagraphStyleDsl().apply(paragraphStyleBlock).build()

        val start = builder.length
        builder.append(content)
        val end = builder.length

        // Добавляем SpanStyle
        if (spanStyle != SpanStyle()) {
            builder.addStyle(spanStyle, start, end)
        }

        // Добавляем ParagraphStyle
        if (paragraphStyle != ParagraphStyle()) {
            builder.addStyle(paragraphStyle, start, end)
        }

        // Добавляем URL аннотацию если есть
        url?.let {
            builder.addStringAnnotation(
                tag = "URL",
                annotation = it,
                start = start,
                end = end
            )
        }
    }

    // Удобные методы-обертки
    fun text(content: String) = styleText(content)

    fun bold(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, spanStyleBlock = {
            fontWeight = FontWeight.Bold
            block()
        })
    }

    fun italic(content: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, spanStyleBlock = {
            fontStyle = FontStyle.Italic
            block()
        })
    }

    fun link(content: String, url: String, block: SpanStyleDsl.() -> Unit = {}) {
        styleText(content, url = url, spanStyleBlock = {
            color = Color.Blue
            textDecoration = TextDecoration.Underline
            block()
        })
    }

    fun append(content: String) = text(content)
    fun appendLine(content: String = "") = text("$content\n")
    fun appendSpace() = text(" ")
}

// DSL для SpanStyle
class SpanStyleDsl {

    var brush: Brush? = null
    var alpha: Float = Float.NaN

    var color: Color = Color.Unspecified
    var fontSize: TextUnit = TextUnit.Unspecified
    var fontWeight: FontWeight? = null
    var fontStyle: FontStyle? = null
    var fontSynthesis: FontSynthesis? = null
    var fontFamily: FontFamily? = null
    var fontFeatureSettings: String? = null
    var letterSpacing: TextUnit = TextUnit.Unspecified
    var baselineShift: BaselineShift? = null
    var textGeometricTransform: TextGeometricTransform? = null
    var localeList: LocaleList? = null
    var background: Color = Color.Unspecified
    var textDecoration: TextDecoration? = null
    var shadow: Shadow? = null
    var platformStyle: PlatformSpanStyle? = null
    var drawStyle: DrawStyle? = null

    fun build(): SpanStyle = if (brush != null) {
        SpanStyle(
            brush = brush,
            alpha = alpha,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFamily = fontFamily,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
        )
    } else {
        SpanStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFamily = fontFamily,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
        )
    }
}

// DSL для ParagraphStyle
class ParagraphStyleDsl {
    var textAlign: TextAlign = TextAlign.Unspecified
    var textDirection: TextDirection = TextDirection.Unspecified
    var lineHeight: TextUnit = TextUnit.Unspecified
    var textIndent: TextIndent? = null
    var platformStyle: PlatformParagraphStyle? = null
    var lineHeightStyle: LineHeightStyle? = null
    var lineBreak: LineBreak = LineBreak.Unspecified
    var hyphens: Hyphens = Hyphens.Unspecified

    fun build(): ParagraphStyle = ParagraphStyle(
        textAlign = textAlign,
        textDirection = textDirection,
        lineHeight = lineHeight,
        textIndent = textIndent,
        platformStyle = platformStyle,
        lineHeightStyle = lineHeightStyle,
        lineBreak = lineBreak,
        hyphens = hyphens
    )
}

fun annotatedString(block: AnnotatedStringBuilderDsl.() -> Unit): AnnotatedString {
    return AnnotatedStringBuilderDsl().apply(block).build()
}