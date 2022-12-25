package danyil.karabinovskyi.weatherapp.core.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import danyil.karabinovskyi.weatherapp.core.components.text.DefaultText
import danyil.karabinovskyi.weatherapp.core.theme.WeatherTheme

@Composable
fun UserInputText(
    innerModifier: Modifier = Modifier,
    outerModifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    textFieldValue: String = "",
    hint: String = "",
    innerPadding: PaddingValues = PaddingValues(0.dp),
    hintColor: Color = Color.White,
    onCancel: () -> Unit = {},
    onSearch: () -> Unit = {},
    textStyle: TextStyle = WeatherTheme.typography.body1SemiBoldUnderlined,
    cursorColor: Color = Color.White,
    maxLength: Int = Int.MAX_VALUE,
    textColor: Color = Color.White,
    searchIconEnabled: Boolean = false,
    iconTint: Color = WeatherTheme.colors.coldGray,
    singleLine: Boolean = true,
    border: Border = Border(),
    iconsEnabled: Boolean = false,
    enabled: MutableState<Boolean> = mutableStateOf(true),
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Row(
        modifier = outerModifier
            .heightIn(0.dp, WeatherTheme.dimensions.ItemSizeSMedium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.Bottom)
                .border(border.width, border.brush, border.shape)
        ) {
            BasicTextField(
                value = textFieldValue,
                modifier = innerModifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .align(Alignment.CenterStart),
                textStyle = textStyle.copy(color = textColor),
                onValueChange = { text: String ->
                    if (text.length <= maxLength)
                        onTextChanged(text)
                },
                enabled = enabled.value,
                cursorBrush = SolidColor(cursorColor),
                singleLine = singleLine,
                keyboardOptions = keyboardOptions
            )
            if (textFieldValue.isEmpty()) {
                DefaultText(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .alpha(0.5f)
                        .padding(start = WeatherTheme.dimensions.SpaceSmall),
                    text = hint,
                    style = WeatherTheme.typography.body2Italic,
                    color = hintColor
                )
            }
            if (iconsEnabled) {
                if (textFieldValue.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier
                            .size(WeatherTheme.dimensions.ItemSizeSSmall)
                            .align(Alignment.CenterEnd)
                            .padding(end = WeatherTheme.dimensions.SpaceSmall),
                        onClick = { onCancel() },
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(WeatherTheme.dimensions.ItemSizeSSmall),
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = iconTint
                        )
                    }
                } else if (searchIconEnabled) {
                    IconButton(
                        modifier = Modifier
                            .size(WeatherTheme.dimensions.ItemSizeSSmall)
                            .align(Alignment.CenterEnd)
                            .padding(end = WeatherTheme.dimensions.SpaceSmall),
                        onClick = { onSearch() }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(WeatherTheme.dimensions.ItemSizeSSmall),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search",
                            tint = iconTint
                        )
                    }
                }
            }
        }
    }
}

class Border(
    val width: Dp = 1.dp,
    val brush: Brush = SolidColor(Color.Transparent),
    val shape: Shape = RoundedCornerShape(8.dp)
)