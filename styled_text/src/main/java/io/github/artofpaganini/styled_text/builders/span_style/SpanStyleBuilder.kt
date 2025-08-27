package io.github.artofpaganini.styled_text.builders.span_style

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.TextUnit

/**
 * A class for decorating text within an AnnotatedString.
 * @property [brush] - Adds a brush effect (gradients) to the text.
 * @property [alpha] - Changes the transparency of the text gradients (only for brush).
 * @property [color] - The color of the text.
 * @property [fontSize] - The font size.
 * @property [fontWeight] - The font weight (bold, normal, etc.).
 * @property [fontStyle] - The font style (italic, normal).
 * @property [fontFamily] - The font family. Combined fonts.
 * @property [fontFeatureSettings] - Additional font settings.
 * @property [letterSpacing] - The spacing between letters.
 * @property [baselineShift] - Shifts the text baseline (up/down).
 * @property [textGeometricTransform] - Applies geometric transformations to the text.
 * @property [background] - The background color of the text.
 * @property [textDecoration] - Text decorations (underline, strikethrough).
 * @property [shadow] - The shadow effect of the text.
 * @property [drawStyle] - The drawing style for the text.
 *
 * Important: The structure of how style properties work in Compose is uniquely built. This means if a certain parameter isn't working,
 * it's likely that another parameter needs to be added as well, as in the example with [alpha].
 */

class SpanStyleBuilder {

    var brush: Brush? = null
    var alpha: Float = Float.NaN
    var color: Color = Color.Unspecified
    var fontSize: TextUnit = TextUnit.Unspecified
    var fontWeight: FontWeight? = null
    var fontStyle: FontStyle? = null
    var fontFamily: FontFamily? = null
    var letterSpacing: TextUnit = TextUnit.Unspecified
    var fontFeatureSettings: String? = null
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
            fontFeatureSettings = fontFeatureSettings,
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
            fontFeatureSettings = fontFeatureSettings,
            textGeometricTransform = textGeometricTransform,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            drawStyle = drawStyle,
        )
    }
}

inline fun spanStyle(block: SpanStyleBuilder.() -> Unit): SpanStyle = SpanStyleBuilder().apply(block).build()
