package danyil.karabinovskyi.weatherapp.features.main_screen.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import danyil.karabinovskyi.weatherapp.R
import danyil.karabinovskyi.weatherapp.core.common.AppStrings
import danyil.karabinovskyi.weatherapp.core.components.input.Border
import danyil.karabinovskyi.weatherapp.core.components.input.UserInputText
import danyil.karabinovskyi.weatherapp.core.components.text.DefaultText
import danyil.karabinovskyi.weatherapp.core.theme.WeatherTheme
import danyil.karabinovskyi.weatherapp.core.utils.debounce
import danyil.karabinovskyi.weatherapp.core.utils.readTimestamp
import danyil.karabinovskyi.weatherapp.features.main_screen.domain.model.Forecast
import danyil.karabinovskyi.weatherapp.features.main_screen.presentation.components.ForecastLazyRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val homeCurrentWeatherState by viewModel.homeForecastState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val inputText = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    val onInputSearch: () -> Unit = debounce(
        500L,
        scope
    ) {
        if (viewModel.searchFieldValue.length > 2) {
            viewModel.searchCity()
        }else {
            viewModel.loadLocation()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(WeatherTheme.dimensions.SpaceXXLarge))
            UserInputText(
                innerModifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                outerModifier = Modifier.padding(
                    top = WeatherTheme.dimensions.SpaceSmall,
                    start = WeatherTheme.dimensions.SpaceSmall,
                    end = WeatherTheme.dimensions.SpaceSmall,
                    bottom = WeatherTheme.dimensions.SpaceSmall
                ),
                innerPadding = PaddingValues(
                    top = WeatherTheme.dimensions.SpaceMedium,
                    bottom = WeatherTheme.dimensions.SpaceSSMedium,
                    start = WeatherTheme.dimensions.SpaceSmall,
                    end = WeatherTheme.dimensions.SpaceXXXLarge
                ),
                textFieldValue = viewModel.searchFieldValue,
                onTextChanged = { text ->
                    viewModel.updateSearchField(text)
                    onInputSearch()
                },
                hint = stringResource(id = R.string.enter_city_name),
                onCancel = {
                    viewModel.updateSearchField("")
                    onInputSearch()
                },
                onSearch = {
                    focusRequester.requestFocus()
                },
                hintColor = WeatherTheme.colors.hint,
                textColor = WeatherTheme.colors.black,
                cursorColor = WeatherTheme.colors.blue,
                iconsEnabled = true,
                searchIconEnabled = true,
                border = Border(
                    brush = if (isSystemInDarkTheme()) SolidColor(WeatherTheme.colors.coldGray) else SolidColor(
                        WeatherTheme.colors.eclipse
                    )
                )
            )

            WeatherSection(homeCurrentWeatherState)
        }
    }
}

@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.bg_dark_theme else R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun WeatherSection(currentWeatherState: HomeState) {
    when (currentWeatherState) {
        is HomeState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressBar(modifier = Modifier.size(WeatherTheme.dimensions.ItemSizeXLarge))
            }
        }
        is HomeState.SuccessOwnCity -> {
            if (currentWeatherState.forecast != null) {
                MainWeatherSection(currentWeatherState.forecast)
                DetailsSection(currentWeatherState.forecast)
            }
        }
        is HomeState.SuccessSearchCity -> {
            if (currentWeatherState.forecast != null) {
                MainWeatherSection(currentWeatherState.forecast)
                DetailsSection(currentWeatherState.forecast)
            }
        }
        is HomeState.Error -> {
            Toast.makeText(
                LocalContext.current,
                currentWeatherState.errorMessage,
                Toast.LENGTH_LONG
            )
        }
    }
}

@Composable
private fun MainWeatherSection(weather: Forecast) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row {
                DefaultText(
                    text = weather.cityDtoData.cityName,
                    style = WeatherTheme.typography.h1.copy(WeatherTheme.colors.primary)
                )
                DefaultText(
                    text = "  --  ${weather.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
                    style = WeatherTheme.typography.h1
                )
            }
            DefaultText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = weather.weatherList[0].weatherStatus[0].description.uppercase(),
                style = WeatherTheme.typography.h2,
                color = WeatherTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
    }
    Column {
        Spacer(modifier = Modifier.height(WeatherTheme.dimensions.SpaceSSSmall))
        CurrentWeatherDetailRow(
            title1 = AppStrings.temp,
            value1 = "${weather.weatherList[0].weatherData.temp}${AppStrings.degree}",
            title2 = AppStrings.feels_like,
            value2 = "${weather.weatherList[0].weatherData.feelsLike}${AppStrings.degree}"
        )
        CurrentWeatherDetailRow(
            title1 = AppStrings.cloudiness,
            value1 = "${weather.weatherList[0].cloudiness.cloudiness}%",
            title2 = AppStrings.humidity,
            value2 = "${weather.weatherList[0].weatherData.humidity}%"
        )
        CurrentWeatherDetailRow(
            title1 = AppStrings.sunrise,
            value1 = "${weather.cityDtoData.sunrise.readTimestamp()}AM",
            title2 = AppStrings.sunset,
            value2 = "${weather.cityDtoData.sunset.readTimestamp()}PM"
        )
        CurrentWeatherDetailRow(
            title1 = AppStrings.wind,
            value1 = "${weather.weatherList[0].wind.speed}${AppStrings.metric}",
            title2 = AppStrings.pressure,
            value2 = "${weather.weatherList[0].weatherData.pressure}"
        )
    }
}

@Composable
private fun DetailsSection(forecast: Forecast) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ForecastSection(forecast)
            }
        }
    }
}

@Composable
private fun ForecastSection(forecastData: Forecast) {
    ForecastTitle(text = AppStrings.hourly_forecast)
    ForecastLazyRow(forecasts = forecastData.weatherList.take(8))
    ForecastTitle(text = AppStrings.daily_forecast)
    ForecastLazyRow(forecasts = forecastData.weatherList.takeLast(32))
}

@Composable
fun CurrentWeatherDetailRow(title1: String, value1: String, title2: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(modifier = Modifier.weight(1f)) {
            CurrentWeatherDetailCard(title = title1, value = value1)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = WeatherTheme.dimensions.SpaceSmall),
            contentAlignment = Alignment.CenterStart
        ) {
            CurrentWeatherDetailCard(title = title2, value = value2)
        }
    }
}

@Composable
private fun CurrentWeatherDetailCard(title: String, value: String) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        DefaultText(text = title, style = WeatherTheme.typography.h3)
        DefaultText(
            text = value,
            style = WeatherTheme.typography.h3
        )
    }
}

@Composable
fun ForecastTitle(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 8.dp),
        text = text,
        style = WeatherTheme.typography.h2.copy(color = WeatherTheme.colors.primary)
    )
}

@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = WeatherTheme.colors.blue,
        strokeWidth = 5.dp
    )
}