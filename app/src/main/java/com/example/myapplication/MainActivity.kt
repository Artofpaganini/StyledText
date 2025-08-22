package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.myapplication.styled_dsl.own.builders.container.styleTextContainer
import com.example.myapplication.styled_dsl.own.builders.paragraph_style.paragraphStyle
import com.example.myapplication.styled_dsl.own.builders.span_style.spanStyle
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    //    val inlineContent = mapOf(
    //        Pair(
    //            // This tells the [CoreText] to replace the placeholder string "[icon]" by
    //            // the composable given in the [InlineTextContent] object.
    //            123,
    //            InlineTextContent(
    //                // Placeholder tells text layout the expected size and vertical alignment of
    //                // children composable.
    //                Placeholder(
    //                    width = 20.sp,
    //                    height = 20.sp,
    //                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
    //                )
    //            ) {
    //                // This Icon will fill maximum size, which is specified by the [Placeholder]
    //                // above. Notice the width and height in [Placeholder] are specified in TextUnit,
    //                // and are converted into pixel by text layout.
    //
    //                Icon(Icons.Filled.Face,"",tint = Color.Red,modifier = Modifier.fillMaxSize())
    //            }
    //        )
    //    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inlineDividerContent = mapOf(
            Pair(
                // This tells the [CoreText] to replace the placeholder string "[divider]" by
                // the composable given in the [InlineTextContent] object.
                123,
                InlineTextContent(
                    // Placeholder tells text layout the expected size and vertical alignment of
                    // children composable.
                    Placeholder(
                        width = 0.15.em,
                        height = 0.90.em,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .rotate(15f)
                            .fillMaxSize()
                            .clip(RectangleShape)
                            .background(Color.DarkGray)
                    )
                }
            )
        )

        val a: AnnotatedString = styleTextContainer {
            styleText {
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
            styleText {
                text = " "
            }
            styleUrl {
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

            styleText {
                text = "      "
            }
            styleText {
                text = "123"
                decorStyle = spanStyle {
                    color = Color.Blue
                    textDecoration = TextDecoration.LineThrough
                }
            }
            styleText {
                text = "123"
                decorStyle = spanStyle {
                    color = Color.Red
                    textDecoration = Underline
                }
            }
            styleText {
                text = " "
            }

            styleUrl {
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

            MyApplicationTheme {
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

val gradientColors = listOf(Cyan, Color.Blue)

val White = Color.White // #FFFFFF
val WhiteSmoke = Color(0xFFF5F5F5) // #F5F5F5
val StringPink = Color(0xFFF81C79) // #F81C79
val GrayishBlue = Color(0xFF9496A2) // #9496A2
val DarkGray = Color(0xFF2F2F2F) // #2F2F2F
val LightGreen = Color(0xFF8BC078) // #8BC078
val DeepCarminePink = Color(0xFFCB5454) // #CB5454
val LightGray = Color(0xFFE9E9E9) // #E9E9E9
val DeepPink = Color(0xFFDC1769) // #DC1769
val Silver = Color(0xFFEEEEEE) // #EEEEEE
val DarkAshen = Color(0xFF595959) //#595959
val LightAshen = Color(0xFFBABABA) //#BABABA
val DarkGreyGradient = Color(0xFF292929) //#292929
val LightGrey = Color(0xFFDCDCDC) // #DDDDDD
val LightPink = Color(0xFFFEDDEB) // #FEDDEB
val SkyPink = Color(0xFFFFEDED) // #FFEDED

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
    MyApplicationTheme {
        Greeting("Android")
    }
}