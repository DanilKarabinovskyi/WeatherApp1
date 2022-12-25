package danyil.karabinovskyi.weatherapp.features.main_screen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import danyil.karabinovskyi.weatherapp.core.components.text.DefaultText
import danyil.karabinovskyi.weatherapp.core.theme.WeatherTheme
import danyil.karabinovskyi.weatherapp.core.utils.WeatherType
import danyil.karabinovskyi.weatherapp.core.utils.convertToHour
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.ForecastWeather

@Composable
fun ForecastLazyRow(forecasts: List<ForecastWeather>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = WeatherTheme.dimensions.SpaceMedium, vertical = WeatherTheme.dimensions.SpaceSSSmall),
        horizontalArrangement = Arrangement.spacedBy(WeatherTheme.dimensions.SpaceSmall)
    ) {
        items(forecasts) {
            if (forecasts.size == 8) {
                WeatherCard(
                    time = it.date.substring(11, 13).convertToHour(),
                    weatherIcon = WeatherType.setWeatherType(
                        it.weatherStatus[0].mainDescription,
                        it.weatherStatus[0].description,
                        it.date.substring(11, 13).convertToHour(),
                    ),
                    degree = "${it.weatherData.temp.toInt()}°"
                )
            } else {
                WeatherCard(
                    date = it.date.substring(5, 10).replace('-', '/'),
                    time = it.date.substring(11, 13).convertToHour(),
                    weatherIcon = WeatherType.setWeatherType(
                        it.weatherStatus[0].mainDescription,
                        it.weatherStatus[0].description,
                        it.date.substring(11, 13).convertToHour(),
                    ),
                    degree = "${it.weatherData.temp.toInt()}°"
                )
            }
        }
    }
}

@Composable
private fun WeatherCard(date: String? = null, time: String, weatherIcon: Int, degree: String) {
    Box(
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = WeatherTheme.dimensions.SpaceSSmall,
                    horizontal = WeatherTheme.dimensions.SpaceSmall
                )
                .background(color = Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(WeatherTheme.dimensions.SpaceSmall),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (date != null) {
                    DefaultText(
                        text = date,
                        style = WeatherTheme.typography.h3.copy(color = WeatherTheme.colors.primary)
                    )
                }
                DefaultText(
                    text = time,
                    style = WeatherTheme.typography.h3.copy(color = WeatherTheme.colors.primary)
                )
            }
            Image(
                modifier = Modifier.size(WeatherTheme.dimensions.ItemSizeXXSmall),
                painter = painterResource(id = weatherIcon),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            DefaultText(
                text = degree,
                style = WeatherTheme.typography.h3.copy(
                    fontSize = 24.sp,
                    color = WeatherTheme.colors.primary
                )
            )
        }
    }
}