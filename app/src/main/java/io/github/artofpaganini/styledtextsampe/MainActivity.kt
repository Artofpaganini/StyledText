package io.github.artofpaganini.styledtextsampe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.artofpaganini.styled_text.builders.container.styledTextContainer
import io.github.artofpaganini.styled_text.builders.span_style.spanStyle
import io.github.artofpaganini.styled_text.builders.text.styledText
import io.github.artofpaganini.styledtextsampe.ui.theme.Purple40
import io.github.artofpaganini.styledtextsampe.ui.theme.StyledTextSampeTheme
import io.github.artofpaganini.styledtextsampe.ui.theme.gradientColors1
import io.github.artofpaganini.styledtextsampe.ui.theme.gradientColors2

//paragraphStyle = paragraphStyle {
//    textAlign = TextAlign.End
//    textIndent = TextIndent(firstLine = 8.sp, restLine = 30.sp)
//    platformStyle = PlatformParagraphStyle(includeFontPadding = false)
//    lineHeightStyle = LineHeightStyle(
//        alignment = LineHeightStyle.Alignment.Center,
//        trim = LineHeightStyle.Trim.None
//    )
//}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inlineId = "imageId"
        val inlineContent = mapOf(
            inlineId to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Image(
                    imageVector = Icons.Default.Call,
//                    modifier = Modifier.size(42.dp),
                    contentDescription = ""
                )
            }
        )
        val title = styledText {
            text = "Welcome!"
            decorStyle = spanStyle {
                brush = Brush.linearGradient(
                    gradientColors1,
                    Offset(0.0f, 0.5f)
                )
                fontWeight = FontWeight.Bold
            }
        }
        val gitHubString = styledTextContainer {
            styledText {
                text = "This"
                decorStyle = spanStyle {
                    color = Color.Red
                    baselineShift = BaselineShift(-1f)
                }
            }
            styledText {
                text = " is my "
                decorStyle = spanStyle {
                    color = Color.Yellow
                    baselineShift = BaselineShift.Subscript
                }
            }
            styledUrl {
                urlTitle = "gitHub"
                url = "https://github.com/Artofpaganini"
                decorStyle = spanStyle {
                    color = Color.Green
                }
                focusedStyle = spanStyle {
                    background = Color(0xFF1565C0)
                    textDecoration = TextDecoration.None
                }
                hoveredStyle = spanStyle {
                    color = Color.Yellow
                    textDecoration = Underline
                }
                pressedStyle = spanStyle {
                    color = Color.Red
                    textDecoration = TextDecoration.LineThrough
                }
            }
        }

        val textWithDifferentStyles = styledTextContainer {
            styledText {
                text = "Some Bold "
                decorStyle = spanStyle {
                    fontSize = 24.sp
                    fontWeight = Bold
                }
            }
            styledText {
                text = " with "
            }
            styledText {
                text = " Italic message "
                decorStyle = spanStyle {
                    fontStyle = FontStyle.Italic
                }
            }
            styledText {
                text = " also with "
            }
            styledText {
                text = " shadows "
                decorStyle = spanStyle {
                    shadow = Shadow(
                        color = Color.Magenta,
                        offset = Offset(3f, 3f),
                        blurRadius = 2f
                    )
                }
            }
            styledText {
                text = " and "
            }
            styledText {
                text = "Background"
                decorStyle = spanStyle {
                    background = Purple40
                }
            }
        }
        val textWithDifferentColor: AnnotatedString = styledText {
            text = "New text where symbols from 7 till 12 have Red color, and other have brush with gradient "
            startIndex = 7
            endIndex = 12
            decorStyle = spanStyle {
                color = Color.Red
            }
            outerDecorStyle = spanStyle {
                brush = Brush.linearGradient(
                    gradientColors2,
                    Offset(0.0f, 0.5f)
                )
                fontWeight = Bold
            }
        }

        val textWithIcon: AnnotatedString = styledTextContainer {
            styledText {
                text = "New text where symbols from 7 till 12 have Red color, and other have brush with gradient "
                startIndex = 7
                endIndex = 12
                decorStyle = spanStyle {
                    color = Color.Red
                }
                inlineContentId = inlineId
                inlineContentIndex = 5

                outerDecorStyle = spanStyle {
                    brush = Brush.linearGradient(
                        gradientColors2,
                        Offset(0.0f, 0.5f)
                    )
                    fontWeight = FontWeight.Bold
                }
            }
            inlineContent {
                id = inlineId
            }
        }
        val a = styledTextContainer {

            styledText {
                text = " "
            }
            styledUrl {
                urlTitle = "AndroidDev"

                decorStyle = spanStyle {
                    color = Color.Green
                }
                focusedStyle = spanStyle {
                    background = Color(0xFF1565C0)
                    textDecoration = Underline
                }
                hoveredStyle = spanStyle {
                    color = Color.Yellow
                    textDecoration = Underline
                }
                pressedStyle = spanStyle {
                    color = Color.Red
                    textDecoration = TextDecoration.LineThrough
                }
            }

            styledText {
                text = "      "
            }
            styledText {
                text = "123"
                decorStyle = spanStyle {
                    color = Color.Blue
                    textDecoration = TextDecoration.LineThrough
                }
            }
            styledText {
                text = "123"
                decorStyle = spanStyle {
                    color = Color.Red
                    textDecoration = Underline
                }
            }
            styledText {
                text = " "
            }

            styledUrl {
                urlTitle = "AndroidDev"
                url = "https://developer.android.com/develop/ui/compose/quick-guides/content/support-multiple-links"
                decorStyle = spanStyle {
                    brush = Brush.linearGradient(
                        gradientColors1,
                        Offset(0.0f, 0.5f)
                    )
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            StyledTextSampeTheme {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                        .safeContentPadding(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val textModifier = Modifier
                        .fillMaxWidth()
                    Text(
                        modifier = textModifier,
                        text = title,
                    )
                    Text(
                        modifier = textModifier,
                        text = gitHubString,
                    )

                    Text(
                        modifier = textModifier,
                        text = textWithDifferentStyles,
                    )
                    Text(
                        modifier = textModifier,
                        text = textWithDifferentColor,
                    )

                    Text(
                        modifier = textModifier,
                        inlineContent = inlineContent,
                        text = textWithIcon,
                    )

                }
            }
        }
    }
}
