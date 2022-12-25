package danyil.karabinovskyi.weatherapp.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: WeatherTypography = WeatherTheme.typography,
    dimensions: WeatherDimensions = WeatherTheme.dimensions,
    shapes: WeatherShapes = WeatherTheme.shapes,
    content: @Composable () -> Unit
) {
    val localColors = if (darkTheme) {
        WeatherColors.defaultDarkColors()
    } else {
        WeatherColors.defaultColors()
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    }

    CompositionLocalProvider(
        LocalColors provides localColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalShapes provides shapes
    ) {
        content()
    }
}

object WeatherTheme {
    val shapes: WeatherShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val colors: WeatherColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: WeatherTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimensions: WeatherDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}
