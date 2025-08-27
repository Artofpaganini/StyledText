package io.github.artofpaganini.styled_text.builders.paragraph_style

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.TextUnit

/**
 * Класс, для стилизации параграфов в переданном тексте
 * @property [textAlign] - Выравнивание текста внутри параграфа. Если указан то имеет приоритет над стандартным TextAlign, внутри Text
 * @property [textDirection] - Направление текста
 * @property [lineHeight] - Высота строки, внутри параграфа. Рекомендуется использовать "em", вместо sp
 * @property [textIndent] - Отступы между параграфами, для первого параграфа и последующих.
 * @property [platformStyle] - Платформо-зависимые настройки
 * @property [lineHeightStyle] - Конфигурация для установки высоты строк.
 * Применяется, если параметр lineHeight и platformStyle(includeFontPadding = false) установлены
 * @property [lineBreak] - Правила переноса строк
 * @property [hyphens] - Правила переноса слов через дефис.
 * @property [textMotion] - Добавление анимационных эффектов для текста.
 *
 * Важно, в Compose уникально выстроена структура работы свойств стилей. Т.е. если какой то параметр не работает,
 * то вероятно для него нужно добавить еще какой то параметр, как в примере с [lineHeightStyle]
 */

class ParagraphStyleBuilder {
    var textAlign: TextAlign = TextAlign.Unspecified
    var textDirection: TextDirection = TextDirection.Unspecified
    var lineHeight: TextUnit = TextUnit.Unspecified
    var textIndent: TextIndent? = null
    var platformStyle: PlatformParagraphStyle? = null
    var lineHeightStyle: LineHeightStyle? = null
    var lineBreak: LineBreak = LineBreak.Unspecified
    var hyphens: Hyphens = Hyphens.Unspecified
    var textMotion: TextMotion? = null

    fun build(): ParagraphStyle = ParagraphStyle(
        textAlign = textAlign,
        textDirection = textDirection,
        lineHeight = lineHeight,
        textIndent = textIndent,
        platformStyle = platformStyle,
        lineHeightStyle = lineHeightStyle,
        lineBreak = lineBreak,
        hyphens = hyphens,
        textMotion = textMotion,
    )
}

inline fun paragraphStyle(block: ParagraphStyleBuilder.() -> Unit): ParagraphStyle =
    ParagraphStyleBuilder().apply(block).build()
