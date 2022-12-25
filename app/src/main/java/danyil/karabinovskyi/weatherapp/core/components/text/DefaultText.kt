package danyil.karabinovskyi.weatherapp.core.components.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import danyil.karabinovskyi.weatherapp.core.theme.WeatherTheme

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = WeatherTheme.colors.primary,
    style: TextStyle = LocalTextStyle.current,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: Int = 1,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = style,
        overflow = overflow,
        onTextLayout = onTextLayout,
        lineHeight = lineHeight,
        maxLines = maxLines,
        textAlign = textAlign,
        textDecoration = textDecoration
    )
}