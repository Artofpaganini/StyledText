package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.styled_dsl.annotatedString1
import com.example.myapplication.styled_dsl.own.StyleTextContainer
import com.example.myapplication.styled_dsl.own.StyleTextPart
import com.example.myapplication.styled_dsl.own.element
import com.example.myapplication.styled_dsl.own.paragraph_style.paragraphStyle
import com.example.myapplication.styled_dsl.own.span_style.spanStyle
import com.example.myapplication.styled_dsl.own.styleBlock
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


        enableEdgeToEdge()
        setContent {

            MyApplicationTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    //                    val simpleText = annotatedString {
                    //                        styleText("Красный текст", spanStyleBlock = {
                    //                            color = Color.Red
                    //                            fontWeight = FontWeight.Bold
                    //                        })
                    //                        appendSpace()
                    //                        styleText("Синий текст", spanStyleBlock = {
                    //                            color = Color.Blue
                    //                            fontStyle = FontStyle.Italic
                    //                        })
                    //                    }
                    //
                    //                    // Пример с ParagraphStyle
                    //                    val textWithAlignment = annotatedString {
                    //                        styleText(
                    //                            content = "Центрированный текст",
                    //                            spanStyleBlock = {
                    //                                fontSize = 18.sp
                    //                                fontWeight = FontWeight.Bold
                    //                            },
                    //                            paragraphStyleBlock = {
                    //                                textAlign = TextAlign.Justify
                    //                                lineHeight = 24.sp
                    //                            }
                    //                        )
                    //                        appendLine()
                    //
                    //                        styleText(
                    //                            content = "Текст с отступом",
                    //                            paragraphStyleBlock = {
                    //                                textIndent = TextIndent(firstLine = 16.sp)
                    //                            }
                    //                        )
                    //                    }

                    // Пример со всеми требованиями
                    //                    val completeExample = buildAdvancedAnnotatedString {
                    //                        // Часть с верхним выравниванием
                    //                        topAligned("Текст с верхним выравниванием") {
                    //                            color = Color.Green
                    //                            fontWeight = FontWeight.Bold
                    //                        }
                    //                        appendLine()
                    //
                    //                        // Кликабельная часть
                    //                        link("Кликабельный текст", "https://github.com/Aghajari/AnnotatedText/tree/main") {
                    //                            fontSize = 16.sp
                    //                        }
                    //                        appendSpace()
                    //
                    //                        // Красный текст
                    //                        red("Красный текст") {
                    //                            fontWeight = FontWeight.Medium
                    //                            background = Color.LightGray
                    //                        }
                    //                        appendSpace()
                    //
                    //                        // Синий текст
                    //                        blue("Синий текст") {
                    //                            fontStyle = FontStyle.Italic
                    //                        }
                    //                        appendSpace()
                    //
                    //                        // Жирный текст
                    //                        bold("Жирный текст") {
                    //                            color = Color.DarkGray
                    //                        }
                    //                    }

                    val fancyText = annotatedString1 {
                        red { text("Красный текст. ") }
                        blue { text("Синий текст. ") }
                        bold { text("Жирный текст. ") }
                        topAligned { text("Надстрочный текст. ") }
                        link("https://github.com/Aghajari/AnnotatedText/tree/main") { bold { text("Кликабельная ссылка") } }
                    }

                    val a: StyleTextContainer = styleBlock {
                        element(
                            part = StyleTextPart.Regular("123"),
                            spanStyle = spanStyle {
                                color = Color.Red
                                textDecoration = TextDecoration.Underline
                            },
                        )
                        element(
                            part = StyleTextPart.Regular(" ")
                        )
                        element(
                            part = StyleTextPart.Url(
                                tag = "AndroidDev",
                                text = "https://developer.android.com/develop/ui/compose/quick-guides/content/support-multiple-links"
                            ),
                            spanStyle = spanStyle {
                                color = UiKitTheme.colors
                                textDecoration = TextDecoration.Underline
                            },
                            paragraphStyle = paragraphStyle {
                                textAlign = TextAlign.Center
                            }
                        )
                        element(
                            part = StyleTextPart.Regular("      ")
                        )
                        element(
                            part = StyleTextPart.Regular("123"),
                            spanStyle = spanStyle {
                                color = Color.Blue
                                textDecoration = TextDecoration.LineThrough
                            }
                        )
                    }
                    val scope = rememberCoroutineScope()
                    Log.w("EWQ", "onCreate: ${a.content}")
                    val ab = remember { mutableStateOf("This text has") }
                    LaunchedEffect(Unit) {
                        scope.launch {
                            for (i in 0..100) {
                                delay(3000)
                                ab.value += ab.value
                            }
                        }
                    }
                    Text(
                        modifier = Modifier
                            .height(300.dp)
                            .width(200.dp)
                            .layout { measurable, constraints ->
                                val placeable = measurable.measure(
                                    // This is how wrapContent works
                                    constraints.copy(minWidth = 0, minHeight = 0)
                                )
                                layout(constraints.maxWidth, constraints.maxHeight) {
                                    // This is how wrapContent alignment works
                                    val x = (constraints.maxWidth - placeable.width) / 2
                                    val y = (constraints.maxHeight - placeable.height) / 2
                                    placeable.placeRelative(x, y)
                                }
                            },
//                        text = ab.value,
                        text = buildAnnotatedString {
                            a.content.forEach { append(it) }
                        },
                        textAlign = TextAlign.Center,
                        overflow = Ellipsis,
                        maxLines = 2,
                    )
                }
            }
        }
    }
}

object UiKitTheme {
    val colors: Color
        @Composable
        @ReadOnlyComposable
        get() = LightGreen
}

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