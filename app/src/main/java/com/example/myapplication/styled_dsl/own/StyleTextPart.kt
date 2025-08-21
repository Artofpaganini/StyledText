package com.example.myapplication.styled_dsl.own

import androidx.compose.runtime.Immutable

@Immutable
sealed interface StyleTextPart {
    val text: String

    data class Url(
        val tag: String,
        override val text: String
    ) : StyleTextPart

    data class Custom(override val text: String) : StyleTextPart

    data class Regular(override val text: String) : StyleTextPart
}