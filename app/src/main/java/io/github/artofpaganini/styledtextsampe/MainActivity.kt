package io.github.artofpaganini.styledtextsampe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.artofpaganini.styled_text.builders.container.styledTextContainer
import io.github.artofpaganini.styled_text.builders.paragraph_style.paragraphStyle
import io.github.artofpaganini.styled_text.builders.span_style.spanStyle
import io.github.artofpaganini.styledtextsampe.ui.theme.StyledTextSampeTheme
import io.github.artofpaganini.styledtextsampe.ui.theme.gradientColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a: AnnotatedString = styledTextContainer {
            styledText {
                text = "Надстрочный текст."
                startIndex = 7
                endIndex = 12
                paragraphStyle = paragraphStyle {
                    textAlign = TextAlign.End
                    textIndent = TextIndent(firstLine = 8.sp, restLine = 30.sp)
                    platformStyle = PlatformParagraphStyle(includeFontPadding = false)
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                }
                decorStyle = spanStyle {
                    color = Color.Red
                    fontSize = 40.sp
                }
            }
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
                        gradientColors,
                        Offset(0.0f, 0.5f)
                    )
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            StyledTextSampeTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = a,
                        textAlign = TextAlign.Center,
                        overflow = Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StyledTextSampeTheme {
        Greeting("Android")
    }
}