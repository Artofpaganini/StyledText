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
 * A class for styling paragraphs in the provided text.
 * @property [textAlign] - Text alignment inside the paragraph. If specified, it takes precedence over the standard TextAlign set within the Text component.
 * @property [textDirection] - The direction of the text.
 * @property [lineHeight] - The height of a line within the paragraph. It is recommended to use "em" units instead of sp.
 * @property [textIndent] - Indentation between paragraphs, for the first and subsequent paragraphs.
 * @property [platformStyle] - Platform-specific settings.
 * @property [lineHeightStyle] - Configuration for setting line heights.
 * Applied if the lineHeight parameter and platformStyle(includeFontPadding = false) are set.
 * @property [lineBreak] - Line breaking rules.
 * @property [hyphens] - Word hyphenation rules.
 * @property [textMotion] - Adds animation effects to the text.
 *
 * Important: The structure of how style properties work in Compose is uniquely built. This means if a certain parameter isn't working,
 * it's likely that another parameter needs to be added as well, as in the example with [lineHeightStyle].
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
