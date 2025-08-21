package com.example.myapplication.spannable_dsl

/**
 * [SpannableModelContainer] позволяет инкапсулировать добавления [element] в список [spannableElementList].
 * P.S по-простому, позволяет убрать "назойливый" метод "add()", при добавлении [element]
 *
 * @property [content] - является списком, в который добавляются [element]'ы, с заданными параметрами.
 * Все добавленные в [content], [element] , будут переданы в метод [spannableContainer] класса [SpannableBuilder]
 * и там присвоен [spannableElementList]
 */
class SpannableModelContainer {

    val content: MutableList<SpannableElement> = mutableListOf()

    @SpannableDSL
    inline fun spannableModelContainer(receiver: SpannableElementBuilder.() -> Unit) {
        SpannableElementBuilder()
            .apply(receiver)
            .build()
            .also { spannableElement ->
                content.add(spannableElement)
            }
    }
}

/**
 *  Extension функция, используемая при работе в контексте [spannableContainer].
 *
 * @param [text] - текст, который нам необходимо как то преобразовать(изменить цвет/стиль/размер)
 * @param [absoluteSize] - размер текста
 * @param [colorRes] - цветовой ресурс текста
 * @param [styleRes] - ресурс стиля текста
 *
 *  Является 1 элементом [spannableModel]
 *  @sample
 *  binding.info.text = spannableModel {
 *              spannableContainer {
 *                  element(
 *                      text = UiText.ByIntRes(item.nameStringRes),
 *                      style = Typeface.BOLD,
 *                  )
 *                  element(
 *                      text = punctuationMark,
 *                      style = Typeface.ITALIC,
 *                      size = 14
 *                  )
 *                  element(
 *                      text = UiText.ByString(item.text),
 *                      color = R.color.white_70,
 *                  )
 *              }
 *          }
 *  Результатом подобного кода является [SpannableModel], состоящая из 3х элементов(слов/строк и тп)
 *  1 элемент имеет текст и стиль [BOLD]
 *  2 элемент имеет текст и стиль [ITALIC]
 *  3 элемент имеет текст и цвет [R.color.white_70]
 */
fun SpannableModelContainer.element(
    text: String,
    relativeSize: Float = DEFAULT_RELATIVE_SIZE_VALUE,
    absoluteSize: Int = DEFAULT_ABSOLUTE_SIZE_VALUE,
    colorRes: Int = DEFAULT_RES_VALUE,
    styleRes: Int = DEFAULT_RES_VALUE,
    colorAttrRes: Int = DEFAULT_ATTRS_VALUE
) = spannableModelContainer {
    this.text = text
    this.absolutSize = absoluteSize
    this.relativeSize = relativeSize
    this.colorRes = colorRes
    this.styleRes = styleRes
    this.colorAttrRes = colorAttrRes
}