package com.example.myapplication.spannable_dsl

/**
 * Аннотация, которая помогает нам контролировать область видимости, при работе со DSL "блоками".
 *
 * @sample
 * spannableModel {
 *       spannableContainer {
 *          var number = 1
 *          element(
 *              text = UiText.ByIntRes(item.nameStringRes),
 *              style = Typeface.BOLD,
 *          )
 *          spannableContainer {
 *               number = 2
 *              .....
 *          }
 *       }
 * }
 *
 * Если не использовать эту аннотацию, то вложенный spannableContainer, будет иметь возможность влиять на контекст,
 * который определен во внешнем spannableContainer(Как показано в @sample), что не допустимо. Каждый контейнер,
 * должен иметь возможность работать только с тем, что доступно ему изнутри.
 * Аннотацией должен помечаться как сам builder так и функция, через которую мы будем работать
 */
@DslMarker
annotation class SpannableDSL