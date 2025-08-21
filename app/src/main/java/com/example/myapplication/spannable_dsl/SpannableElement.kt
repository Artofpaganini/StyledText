package com.example.myapplication.spannable_dsl

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.core.text.toHtml
import androidx.core.text.toSpannable


const val DEFAULT_RES_VALUE = -1
const val DEFAULT_ABSOLUTE_SIZE_VALUE = -1
const val DEFAULT_RELATIVE_SIZE_VALUE = -1.0f
const val DEFAULT_ATTRS_VALUE = -1

/**
 * SpannableElement - класс, который описывает минимальную часть Spannable текста, с необходимыми
 * параметрами стилизации.
 * Может использоваться как часть SpannableModel
 * @param [text] текст, который нам необходимо как то преобразовать(изменить цвет/стиль/размер)
 * @param [textSize] размер текста
 * @param [textColorRes] цветовой ресурс текста
 * @param [textStyle] ресурс стиля текста
 * @param [textColorAttrRes] цвет из атрибута для текста (смена темы приложения)
 * /n
 * Класс, можно расширить любыми возможностями, которые предоставляет Spannable, добавив необходимые параметры
 * стилизации, к уже имеющимся.
 */
data class SpannableElement(
    val text: String,
    val textAbsoluteSize: Int,
    val textRelativeSize: Float,
    @ColorRes val textColorRes: Int,
    val textStyle: Int,
    @AttrRes val textColorAttrRes: Int,
) {

    fun toSpannable(context: Context): Spannable {
        val spannableStr = SpannableString(text)
        setSpannableParams(
            context = context,
            textPath = spannableStr
        )
        return spannableStr.toSpannable()
    }

    @Composable
    fun toAnnotatedString(context: Context): AnnotatedString {
        val spannableStr = SpannableString(text)
        setSpannableParams(
            context = context,
            textPath = spannableStr
        )
        val hmlString = spannableStr.toHtml()
        return AnnotatedString.fromHtml(hmlString)
    }

    /**
     * Метод применения заданных параметров стилизации к тексту. Возможно изменение цвета/стиля/размера и тд... текста
     * @param [textPath] - текст, к которому необходимо применить параметры стилизации
     */
    private fun setSpannableParams(context: Context, textPath: SpannableString) {

    }
}