package io.github.artofpaganini.styled_text.builders.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString

fun AnnotatedString.withLink(
    onLinkClick: (link: String) -> Unit
): AnnotatedString {
    if (isEmpty() || !hasLinkAnnotations(0, lastIndex)) return this
    val currentText = this
    return buildAnnotatedString {
        append(currentText)
        currentText.getLinkAnnotations(0, currentText.lastIndex)
            .filter { it.item is LinkAnnotation.Clickable }
            .forEach { range ->
                if (range.item.linkInteractionListener == null) {
                    val link = range.item as LinkAnnotation.Clickable
                    addLink(
                        clickable = link.copy(linkInteractionListener = { onLinkClick(link.tag) }),
                        start = range.start,
                        end = range.end
                    )
                }
            }
    }
}