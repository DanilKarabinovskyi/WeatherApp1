package danyil.karabinovskyi.weatherapp.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class WeatherShapes(
    val RoundedCornerShapeSSSSmall: Shape = RoundedCornerShape(2.dp),
    val RoundedCornerShapeSSSmall: Shape = RoundedCornerShape(4.dp),
    val RoundedCornerShapeSSmall: Shape = RoundedCornerShape(6.dp),
    val RoundedCornerShapeSmall: Shape = RoundedCornerShape(8.dp),
)

internal val LocalShapes = staticCompositionLocalOf { WeatherShapes() }