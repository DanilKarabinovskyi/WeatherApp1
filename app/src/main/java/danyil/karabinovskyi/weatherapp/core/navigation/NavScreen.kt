package danyil.karabinovskyi.weatherapp.core.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(NavRoutes.homeScreen)
}
