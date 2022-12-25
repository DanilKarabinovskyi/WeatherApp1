package danyil.karabinovskyi.weatherapp.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WeatherDimensions(
    val SpaceSSSSmall: Dp = 2.dp,
    val SpaceSSSmall: Dp = 4.dp,
    val SpaceSSmall: Dp = 6.dp,
    val SpaceSmall: Dp = 8.dp,
    val SpaceXSmall: Dp = 10.dp,
    val SpaceSSMedium: Dp = 12.dp,
    val SpaceSMedium: Dp = 14.dp,
    val SpaceMedium: Dp = 16.dp,
    val SpaceLarge: Dp = 24.dp,
    val SpaceXLarge: Dp = 32.dp,
    val SpaceXXLarge: Dp = 40.dp,
    val SpaceXXXLarge: Dp = 56.dp,
    val SpaceSSUltraLarge: Dp = 90.dp,
    val SpaceSUltraLarge: Dp = 130.dp,
    val SpaceUltraLarge: Dp = 150.dp,
    val ItemSizeSSSSSmall: Dp = 12.dp,
    val ItemSizeUltraSmall: Dp = 4.dp,
    val ItemSizeSSSmall: Dp = 18.dp,
    val ItemSizeSSmall: Dp = 24.dp,
    val ItemSizeSmall: Dp = 28.dp,
    val ItemSizeXSmall: Dp = 32.dp,
    val ItemSizeXXSmall: Dp = 36.dp,
    val ItemSizeSSSSMedium: Dp = 44.dp,
    val ItemSizeSSSMedium: Dp = 46.dp,
    val ItemSizeSMedium: Dp = 48.dp,
    val ItemSizeMedium: Dp = 50.dp,
    val ItemSizeXMedium: Dp = 58.dp,
    val ItemSizeSSLarge: Dp = 64.dp,
    val ItemSizeSLarge: Dp = 68.dp,
    val ItemSizeLarge: Dp = 75.dp,
    val ItemSizeXLarge: Dp = 85.dp,
    val ItemSizeUltraLarge: Dp = 150.dp,
)

internal val LocalDimensions = staticCompositionLocalOf { WeatherDimensions() }