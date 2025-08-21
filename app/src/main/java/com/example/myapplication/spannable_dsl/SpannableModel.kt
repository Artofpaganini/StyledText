package com.example.myapplication.spannable_dsl

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.core.text.toHtml
import androidx.core.text.toSpannable

/**
 * [SpannableModel] - класс который описывает Spannable текст, состоящий из текстовых элементов - SpannableElement's,
 * с необходимимыми параметрами стилизации
 * @param [input] - список, в который собираются все текстовые элементы.
 *
 * ВАЖНО!!! Для создания объекта [SpannableModel], используйте функцию [spannableModel]
 */
data class SpannableModel(
    val input: List<SpannableElement>
) {

    /**
     * Функция преобразует список [input], в единую [Spannable] строку, с заданными,
     * для каждого элемента списка[input] параметрами
     */
    fun toSpannable(context: Context): Spannable {
        val spannableBuilder = SpannableStringBuilder()

        input.map { pathOfStr ->
            val modifiedText = pathOfStr.toSpannable(context)
            spannableBuilder.append(modifiedText)
        }
        return spannableBuilder.toSpannable()
    }

    fun toAnnotatedString(context: Context): AnnotatedString {
        val spannableBuilder = SpannableStringBuilder()
        input.map { pathOfStr ->
            val modifiedText = pathOfStr.toSpannable(context)
            spannableBuilder.append(modifiedText)
        }
        val spannableStr = spannableBuilder.toSpannable()
        val hmlString = spannableStr.toHtml()
        return AnnotatedString.fromHtml(hmlString)
    }

    /**
     * Функция преобразует список [input], в единую [Spannable] строку, с заданными,
     * для каждого элемента списка[input] параметрами
     * Учитывает направление текста в Rtl языках
     */
//    fun toSpannableWithRtl(context: Context): Spannable {
//        val spannableBuilder = SpannableStringBuilder()
//        val bidiFormatter = BidiFormatter.getInstance(BidiUtils.isRtl())
//
//        input.map { pathOfStr ->
//            val modifiedText = pathOfStr.toSpannable(context)
//            val rtlText = bidiFormatter.unicodeWrap(modifiedText)
//            spannableBuilder.append(rtlText)
//        }
//        return spannableBuilder.toSpannable()
//    }

    fun getString(): String {
        return input.joinToString { spannableElement -> spannableElement.text }
    }
}