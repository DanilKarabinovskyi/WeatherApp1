package danyil.karabinovskyi.weatherapp.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val colorBlack = Color(0xFF000000)
private val colorColdGray = Color(0xFFCCD6E2)
private val lightBlack = Color(0x3F000000)
private val colorWhite = Color(0xFFFFFFFF)
private val colorHint = Color(0xBF888888)
private val colorGray = Color(0xFFE5E5E5)
private val colorSmokeGray = Color(0xFFF0F0F0)
private val colorWhiteOpacity80 = Color(0x80FFFFFF)
private val colorBlue = Color(0xFF009ed3)
private val colorEclipse = Color(0xFF363636)
private val colorNero = Color(0xFF222222)

@Immutable
data class WeatherColors(
    val primary: Color = colorWhite,
    val background: Color = colorBlack,
    val black: Color = colorBlack,
    val coldGray: Color = colorColdGray,
    val textSecondary: Color = colorGray,
    val white: Color = colorWhite,
    val hint: Color = colorHint,
    val gray: Color = colorGray,
    val smokeGray: Color = colorSmokeGray,
    val eclipse: Color = colorEclipse,
    val blue: Color = colorBlue,
) {

    companion object {

        @Composable
        fun defaultColors() = WeatherColors(
            primary = colorBlack,
            black = colorBlack,
            coldGray = colorColdGray,
            textSecondary = lightBlack,
            white = colorWhite,
            hint = colorGray,
            gray = colorGray,
            smokeGray = colorSmokeGray,
            eclipse = colorEclipse,
            blue = colorBlue,
            background = colorSmokeGray
        )

        @Composable
        fun defaultDarkColors() = WeatherColors(
            primary = colorWhite,
            black = colorBlack,
            coldGray = colorColdGray,
            textSecondary = colorGray,
            white = colorWhite,
            hint = colorGray,
            gray = colorGray,
            smokeGray = colorSmokeGray,
            eclipse = colorEclipse,
            blue = colorBlue,
            background = colorNero
        )
    }
}

internal val LocalColors = staticCompositionLocalOf { WeatherColors() }