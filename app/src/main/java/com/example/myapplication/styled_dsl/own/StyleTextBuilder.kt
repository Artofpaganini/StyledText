package com.example.myapplication.styled_dsl.own

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.withLink

class StyleTextBuilder {
    private val builder = AnnotatedString.Builder()

    var part: StyleTextPart? = null
    var spanStyle: SpanStyle? = null
    var paragraphStyle: ParagraphStyle? = null

    fun build(): AnnotatedString = builder.apply {
        when (val type = part) {
            is StyleTextPart.Url -> {
                val linkAnnotation = Url(
                    url = type.text,
                    styles = spanStyle?.let(::TextLinkStyles),
                )
                addLink(url = linkAnnotation, start = 0, end = length)
                withLink(linkAnnotation) {
                    append(type.tag)
                }
            }
            is StyleTextPart.Regular -> {
                append(type.text)
                paragraphStyle?.let { paragraph ->
                    addStyle(
                        style = paragraph,
                        start = 0,
                        end = length
                    )
                }
                spanStyle?.let { span ->
                    addStyle(
                        style = span,
                        start = 0,
                        end = length
                    )
                }
            }
            else -> return@apply
        }
    }.toAnnotatedString()
}

inline fun styledElement(block: StyleTextBuilder.() -> Unit): AnnotatedString = StyleTextBuilder().apply(block).build()

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

