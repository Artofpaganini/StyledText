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
 * Класс, для декорирования текста annotatedString,
 * @property [brush] - Добавление brush-эффекта(градиенты)
 * @property [alpha] - Изменение прозрачности градиентов текста (только для brush)
 * @property [color] - Цвет текста
 * @property [fontSize] - Размер шрифта
 * @property [fontWeight] - Насыщенность шрифта (жирный, нормальный и т.д.)
 * @property [fontStyle] - Стиль шрифта (курсив, нормальный)
 * @property [fontFamily] - Семейство шрифтов. Комбинированные шрифты
 * @property [fontFeatureSettings] - Дополнительные настройки шрифта
 * @property [letterSpacing] - Межбуквенное расстояние
 * @property [baselineShift] - Смещение текста (вверх/вниз),
 * @property [textGeometricTransform] - Геометрические трансформации текста
 * @property [background] - Цвет фона текста
 * @property [textDecoration] - Декорации текста (подчеркивание, зачеркивание)
 * @property [shadow] - Тень текста
 * @property [drawStyle] - Стиль отрисовки текста
 *
 * Важно, в Compose уникально выстроена структура работы свойств стилей. Т.е. если какой то параметр не работает,
 * то вероятно для него нужно добавить еще какой то параметр, как в примере с [alpha]
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
