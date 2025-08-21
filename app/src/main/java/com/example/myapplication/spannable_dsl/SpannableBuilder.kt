package com.example.myapplication.spannable_dsl

/**
 *  Билдер, конфигурирующий список [SpannableElement], с заданными параметрами.
 *  Работа осуществляется, путем вызова функции [spannableModel], где в качестве [receiver] используется
 *  [SpannableBuilder], в который передаются необходимые параметры
 *
 *  @property [spannableElementList] - список, в который собираются все [SpannableElement]. Список
 *  формируется с помощью метода [spannableContainer].
 *
 */
@SpannableDSL
class SpannableBuilder {

    private val spannableElementList: MutableList<SpannableElement> = mutableListOf()

    fun build(): SpannableModel = SpannableModel(spannableElementList)

    /**
     * Функция, которая генерирует внутри себя контейнер [SpannableModelContainer], со списком. После чего
     * его [SpannableModelContainer.content] добавляется в @param [spannableElementList].
     *
     * @params (receiver: SpannableModelContainer.() -> Unit) - лямбда с объектом-приёмником.
     * Это значит, что вам нужно передать в лямбду список [SpannableModelContainer.element]
     *
     * Подробнее об лямбдах с объектом-приёмником можно ознакомиться тут:
     * @see https://kotlinlang.ru/docs/lambdas.html#function-literals-with-receiver
     *
     * P.S Если по-простому, то в [spannableElementList], будут добавлены те [SpannableModelContainer.element], которые
     * вы положили в [receiver]. И добавлены они будут в порядке добавления в [receiver].
     *
     * Важно! DSL-domain-specific languages, является полу-декларативным. Поэтому названия методов должны отражать
     * суть того чем они являются.
     */
    fun spannableContainer(receiver: SpannableModelContainer.() -> Unit) {
        val container = SpannableModelContainer().apply(receiver)
        spannableElementList.addAll(container.content)
    }
}

/**
 * @params (receiver: SpannableBuilder.() -> Unit) - лямбда с объектом-приёмником.
 * Это значит, что вам нужно в лямбде, перечислить [SpannableModelContainer.element], с заданными параметрами
 *
 * Подробнее об лямбдах с объектом-приёмником можно ознакомиться тут:
 * @see https://kotlinlang.ru/docs/lambdas.html#function-literals-with-receiver
 *
 * P.S Если по-простому, то в @param [receiver] будет сформирован объект [SpannableBuilder],
 * с заданным списком элементов [spannableElementList]. На выходе мы получим [SpannableModel]
 *
 * @sample
 *  val item = spannableModel {
 *      spannableContainer {
 *          element(
 *              text = UiText.ByIntRes("Hello "),
 *              style = Typeface.BOLD,
 *              color = R.color.pink,
 *          )
 *          element(
 *              text = UiText.ByIntRes("World"),
 *              style = Typeface.ITALIC,
 *              size = 14
 *          )
 *          element(
 *              text = UiText.ByString("!"),
 *              color = R.color.yellow,
 *              size = 20
 *          )
 *      }
 *  item.toSpannable(context)
 *
 *  В результате, на экране будет отображаться надпись "Hello World!", где
 *  Hello - имеет жирный шрифт и розовый цвет, размер текста соответсвует указанному в TextView
 *  World - имеет курсивный шрифт и цвет заданный в TextView, 14 sp размер текста
 *  ! - имеет заданный в TextView шрифт и желтый цвет, 20 sp размер текста
 */
inline fun spannableModel(receiver: SpannableBuilder.() -> Unit): SpannableModel =
    SpannableBuilder()
        .apply(receiver)
        .build()