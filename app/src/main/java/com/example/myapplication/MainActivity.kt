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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                                color = Color.Blue
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
                    Log.w("EWQ", "onCreate: ${a.content}")
                    Text(
                        modifier = Modifier.height(50.dp).verticalCenter(50.dp),
                        text = "This text has a custom line height.",
                        overflow = Ellipsis,
                        maxLines = 2,
                    )
                }
            }
        }
    }
}


@SuppressLint("SuspiciousModifierThen")
fun Modifier.verticalCenter(fixedHeightPx: Dp) = this.then(
    layout { measurable, constraints ->
        // Ограничиваем измерение дочернего элемента только по его содержимому
        val placeable = measurable.measure(
            constraints.copy(minHeight = 0, maxHeight = fixedHeightPx.roundToPx())
        )

        val yOffset = ((fixedHeightPx.roundToPx() - placeable.height) / 2).coerceAtLeast(0)

        layout(constraints.maxWidth, fixedHeightPx.roundToPx()) {
            placeable.placeRelative(0, yOffset)
        }
    }
)

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