package com.example.myapplication.spannable_dsl

/**
 *  Билдер, конфигурирующий один [SpannableElement], с заданными параметрами.
 *  Работа осуществляется, путем вызова функции [spannableElement], где в качестве [receiver] используется
 *  [SpannableElementBuilder], в который передаются необходимые параметры
 */
@SpannableDSL
class SpannableElementBuilder {

    var text: String = ""
    var absolutSize: Int = DEFAULT_ABSOLUTE_SIZE_VALUE
    var relativeSize: Float = DEFAULT_RELATIVE_SIZE_VALUE
    var colorRes: Int = DEFAULT_RES_VALUE
    var styleRes: Int = DEFAULT_RES_VALUE
    var colorAttrRes: Int = DEFAULT_ATTRS_VALUE

    /**
     * Данная ф-ция используется только в рамках DSL, поэтому ее вызов должен осуществляться
     * только в рамках [spannableElement]
     */
    fun build(): SpannableElement = SpannableElement(
        text = text,
        textAbsoluteSize = absolutSize,
        textRelativeSize = relativeSize,
        textColorRes = colorRes,
        textStyle = styleRes,
        textColorAttrRes = colorAttrRes
    )
}

/**
 * @params (receiver: SpannableElementBuilder.() -> Unit) - лямбда с объектом-приёмником.
 * Это значит, что вам нужно передать в лямбду, заданные параметры, для создания [SpannableElement]
 *
 * Подробнее об лямбдах с объектом-приёмником можно ознакомиться тут:
 * @see https://kotlinlang.ru/docs/lambdas.html#function-literals-with-receiver
 *
 * P.S Если по-простому, то в @param [receiver] будет сформирован объект [SpannableElementBuilder],
 * с заданными, в лямбде, параметрами. На выходе мы получим [SpannableElement]
 *
 *  @samples
 *  val item : SpannableElement  = spannableElement {
 *      text = UiText.ByIntRes("SomeText")
 *      style = Typeface.BOLD
 *      color = R.color.green
 *  }
 *  item.toSpannable(context)
 *
 *  В результате, на экране будет отображаться надпись "SomeText", жирным шрифтом, зеленого цвета.
 */
@SpannableDSL
inline fun spannableElement(receiver: SpannableElementBuilder.() -> Unit): SpannableElement =
    SpannableElementBuilder()
        .apply(receiver)
        .build()