package com.example.myapplication.styled_dsl

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withLink

class AnnotatedStringDslBuilder123 {
    private
    val builder = AnnotatedString.Builder()

    fun text(text: String) {
        builder.append(text)
    }

    fun styled(style: SpanStyle, block: AnnotatedStringDslBuilder123.() -> Unit) {
        val start = builder.length
        block()
        val end = builder.length
        builder.addStyle(style, start, end)
    }

    fun annotation(tag: String, annotation: String, block: AnnotatedStringDslBuilder123.() -> Unit) {
        val start = builder.length
        block()
        val end = builder.length
//        builder.withLink(LinkAnnotation.Url(annotation)){
//            append(tag)
//        }
        builder.addLink(LinkAnnotation.Url(annotation), start, end)
    }

    fun build(): AnnotatedString = builder.toAnnotatedString()

    // -------- Дополнительные упрощающие функции --------
    fun red(block: AnnotatedStringDslBuilder123.() -> Unit) =
        styled(SpanStyle(color = Color.Red), block)

    fun blue(block: AnnotatedStringDslBuilder123.() -> Unit) =
        styled(SpanStyle(color = Color.Blue), block)

    fun bold(block: AnnotatedStringDslBuilder123.() -> Unit) =
        styled(SpanStyle(fontWeight = FontWeight.Bold), block)

    fun topAligned(block: AnnotatedStringDslBuilder123.() -> Unit) =
        styled(SpanStyle(baselineShift = BaselineShift.Superscript), block)

    fun link(url: String, block: AnnotatedStringDslBuilder123.() -> Unit) =
        annotation("URL", url) { styled(SpanStyle(color = Color.Blue), block) }
}


fun annotatedString1(block: AnnotatedStringDslBuilder123.() -> Unit): AnnotatedString {
    val dsl = AnnotatedStringDslBuilder123()
    dsl.block()
    return dsl.build()
}
