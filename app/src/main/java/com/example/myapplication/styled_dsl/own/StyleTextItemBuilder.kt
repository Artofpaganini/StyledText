package com.example.myapplication.styled_dsl.own

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.example.myapplication.styled_dsl.own.annotations.DslStyleTextItem

@DslStyleTextItem
class StyleTextItemBuilder {

    private val itemBuilder = AnnotatedString.Builder()

    var type: StyleTextType? = null
    var startIndex: Int = 0
    var endIndex: Int = 0
    var spanStyle: SpanStyle? = null

    fun build(): AnnotatedString = itemBuilder.apply {
        when (val type = type) {
            is StyleTextType.Url -> {
                val linkAnnotation = Url(
                    url = type.text,
                    styles = TextLinkStyles(
                        style = spanStyle,
                        focusedStyle = SpanStyle(
                            color = Color(0xFF1565C0),
                            textDecoration = Underline,
                            background = Color(0x330000FF),
                        ),
                        pressedStyle = SpanStyle(
                            color = Color.Red, // Красный цвет при нажатии
                            textDecoration = Underline,
                            background = Color(0x66FF0000) // Красная подсветка
                        )
                    ),
                )
                addLink(url = linkAnnotation, start = 0, end = length)
                withLink(linkAnnotation) {
                    append(type.tag)
                }
            }
            is StyleTextType.Regular -> spanStyle?.let { style ->
                if (startIndex != 0 && endIndex != 0) {
                    append(type.text)
                    addStyle(style = style, start = startIndex, end = endIndex)
                } else {
                    withStyle(style) { append(type.text) }
                }
            } ?: append(type.text)

            else -> return@apply
        }
    }.toAnnotatedString()
}

@DslStyleTextItem
inline fun styleTextItem(receiver: StyleTextItemBuilder.() -> Unit): AnnotatedString =
    StyleTextItemBuilder().apply(receiver).build()

//private fun AnnotatedString.Builder.applyStyle(
//    styledString: StyledString,
//    startIndex: Int,
//    endIndex: Int,
//    onClick: (String) -> Unit
//) {
//    when (val type = styledString) {
//        is StyledString.ClickableUrl -> {
//            val linkAnnotation = LinkAnnotation.Url(
//                url = styledString.url,
//                styles = TextLinkStyles(style = styledString.style),
//            )
//            addLink(linkAnnotation, startIndex, endIndex)
//        }
//        is StyledString.ClickableCustom -> {
//            val linkAnnotation = LinkAnnotation.Clickable(
//                tag = styledString.text,
//                styles = TextLinkStyles(style = styledString.style),
//                linkInteractionListener = { onClick(type.text) }
//            )
//            addLink(linkAnnotation, startIndex, endIndex)
//        }
//
//        is StyledString.Regular -> {
//            addStyle(
//                style = styledString.style,
//                start = startIndex,
//                end = endIndex
//            )
//        }
//    }
//}

