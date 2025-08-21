package com.example.myapplication.styled_dsl.own

import androidx.compose.runtime.Immutable

@Immutable
sealed interface StyledString {

    data class ClickableUrl(val url: String) : StyledString

    data class ClickableCustom(val text: String) : StyledString

    data class Regular(val text: String)
}