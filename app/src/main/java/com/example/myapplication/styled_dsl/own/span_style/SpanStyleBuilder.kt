package com.example.myapplication.styled_dsl.own.span_style

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.TextUnit

class SpanStyleBuilder {

    var brush: Brush? = null
    var alpha: Float = Float.NaN

    var color: Color = Color.Unspecified
    var fontSize: TextUnit = TextUnit.Unspecified
    var fontWeight: FontWeight? = null
    var fontStyle: FontStyle? = null
    var fontFamily: FontFamily? = null
    var letterSpacing: TextUnit = TextUnit.Unspecified
    var baselineShift: BaselineShift = BaselineShift.None
    var textGeometricTransform: TextGeometricTransform? = null
    var background: Color = Color.Unspecified
    var textDecoration: TextDecoration = TextDecoration.None
    var shadow: Shadow? = null
    var drawStyle: DrawStyle? = null

    fun build(): SpanStyle = if (brush != null) {
        SpanStyle(
            brush = brush,
            alpha = alpha,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            drawStyle = drawStyle,
        )
    } else {
        SpanStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            drawStyle = drawStyle,
        )
    }
}

inline fun spanStyle(block: SpanStyleBuilder.() -> Unit): SpanStyle = SpanStyleBuilder().apply(block).build()
