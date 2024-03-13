package no.uio.ifi.in2000.team11.havvarselapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocatinForecastRepositoryImpl
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocationForecastDataSource
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocationForecastRepository
import no.uio.ifi.in2000.team11.havvarselapp.ui.theme.HavvarselAppTheme

import no.uio.ifi.in2000.team11.havvarselapp.data.weather_current_waves.GripfilesDataSource
import no.uio.ifi.in2000.team11.havvarselapp.ui.LocationForecast.LocationForecastViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HavvarselAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
           // TestGribfilesDataSource()
           // TestLocationForecastDataSource()
           // TestLocationRepository()
           // TestLocationForecastViewModel()
        }
    }
}



@Composable
fun TestGribfilesDataSource() {
    val dataSource = GripfilesDataSource()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val weatherList = dataSource.fetchWeatherCurrentWaves()

                weatherList.groupedWeatherCurrentWaves.forEach { (area, dataList) ->
                    dataList.forEach { data ->
                        Log.e("WEATHER_DATA: ", " \nArea: $area, \nContent: ${data.params.content}, \nUpdated: ${data.updated}, \nURI: ${data.uri}\n ")
                    }
                }

            } catch (e: Exception) {
                // Logg eventuelle feil
                Log.e("ERROR WEATHER_DATA", "feilmelding: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}










@Composable
fun TestLocationForecastDataSource() {
    val dataSource = LocationForecastDataSource()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val locationForecast = dataSource.fetchLocationForecast_LatAndLon( "59.9", "10.7")
                Log.e("2-LOCATIONFORECAST_DATA: ", " \n\nDATA SOURCE: CURRENT WHEATER DATA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                        "\nUV-Index: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.ultraviolet_index_clear_sky} ${locationForecast.properties.meta.units.ultraviolet_index_clear_sky}" +
                        "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                        "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n\n")



            } catch (e: Exception) {
                // Logg eventuelle feil
                Log.e("2-ERROR LOCATIONFORECAST_DATA", "feilmelding: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}

@Composable
fun TestLocationRepository() {
    val repository = LocatinForecastRepositoryImpl()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val locationForecast = repository.getLocationForecast("59.9", "10.7")
                if (locationForecast != null) {
                    Log.e("REPOSITORY-LOCATIONFORECAST: ", " \n\nREPOSOTORY: CURRENT WHEATER DATA\n " +
                            "\nCordinates: ${locationForecast.geometry.coordinates} " +
                            "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                            "\nUV-Index: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.ultraviolet_index_clear_sky} ${locationForecast.properties.meta.units.ultraviolet_index_clear_sky}" +
                            "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                            "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n\n")
                }


            } catch (e: Exception) {
                Log.e("ERROR forecast REPOSITORY", "Feil ved LocatinForecastRepositoryImpl()...", e)
            }
        }
    }
}



@Composable
fun TestLocationForecastViewModel() {
    val forecastViewModel = LocationForecastViewModel()
    val state = forecastViewModel.forecastInfo_UiState.collectAsState()

    val coordinater = Pair("59.9", "10.7")
    LaunchedEffect(coordinater) {
        forecastViewModel.loadForecast(coordinater.first, coordinater.second)
    }
            try {
                state.value?.let { locationForecast ->
                    Log.e("ViewModel-LOCATIONFORECAST: ", " \n\nVIEW-MODEL: CURRENT WHEATER DATA\n " +
                            "\nCordinates: ${locationForecast.geometry.coordinates} " +
                            "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                            "\nUV-Index: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.ultraviolet_index_clear_sky} ${locationForecast.properties.meta.units.ultraviolet_index_clear_sky}" +
                            "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                            "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n\n")

                }

            } catch (e: Exception) {
                // Logg eventuelle feil
                Log.e("ERROR VIEWMODEL FORECAST", "feilmelding: ${e.message}")
                e.printStackTrace()
            }


}







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
    HavvarselAppTheme {
        Greeting("Android")
    }
}


