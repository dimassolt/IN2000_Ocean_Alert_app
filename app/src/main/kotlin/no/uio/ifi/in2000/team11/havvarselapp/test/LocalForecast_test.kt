package no.uio.ifi.in2000.team11.havvarselapp.test

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocatinForecastRepositoryImpl
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocationForecastDataSource
import no.uio.ifi.in2000.team11.havvarselapp.ui.LocationForecast.LocationForecastViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.time.ZonedDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TestLocationForecastDataSource() {
    val dataSource = LocationForecastDataSource()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val locationForecast = dataSource.fetchLocationForecast_LatAndLon( "59.9", "10.7")
                val tid_String: String = "${locationForecast.properties.timeseries.firstOrNull()?.time}"
                var parsedDate = ZonedDateTime.parse(tid_String)
                val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm")
                val tid = parsedDate.withZoneSameInstant(ZoneId.of("Europe/Oslo")).format(formatter)

                Log.e("LOCATIONFORECAST_DATA NOW: ", " \n\nDATA-SOURCE LOCATIONFORECAST: CURRENT WHEATER DATA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nDato og Tid: ${locationForecast.properties.timeseries.firstOrNull()?.time} " +
                        "\nParsed Tid: $tid " +
                        "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                        "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                        "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n" +
                        "\nAir pressure at sea level : ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_pressure_at_sea_level} ${locationForecast.properties.meta.units.air_pressure_at_sea_level}" +
                        "\ncloud area fraction : ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.cloud_area_fraction} ${locationForecast.properties.meta.units.cloud_area_fraction}" +
                        "\nRelative_humidity: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.relative_humidity} ${locationForecast.properties.meta.units.relative_humidity}\n\n")

                Log.e("LOCATIONFORECAST_DATA 1 hour: ", " \n\nDATA-SOURCE LOCATIONFORECAST: CURRENT WHEATER DATA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nDato og Tid: ${locationForecast.properties.timeseries[2]?.time} " +
                        "\nTemperature: ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                        "\nWind speed: ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                        "\nWind direction: ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n" +
                        "\nAir pressure at sea level : ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.air_pressure_at_sea_level} ${locationForecast.properties.meta.units.air_pressure_at_sea_level}" +
                        "\ncloud area fraction : ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.cloud_area_fraction} ${locationForecast.properties.meta.units.cloud_area_fraction}" +
                        "\nRelative_humidity: ${locationForecast.properties.timeseries[2]?.data?.instant?.details?.relative_humidity} ${locationForecast.properties.meta.units.relative_humidity}\n\n")

                /**
                Log.e("LOCATIONFORECAST_DATA 1 hour: ", " \n\nDATA-SOURCE LOCATIONFORECAST:  WHEATER DAT IN 1 HOURA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nNedbørs mengde: ${locationForecast.properties.timeseries.firstOrNull()?.data?.next_1_hours?.details?.precipitation_amount} ${locationForecast.properties.meta.units.precipitation_amount}\n\n")

                Log.e("LOCATIONFORECAST_DATA 6 hour: ", " \n\nDATA-SOURCE LOCATIONFORECAST:  WHEATER DAT IN 6 HOURA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nNedbørs mengde: ${locationForecast.properties.timeseries.firstOrNull()?.data?.next_6_hours?.details?.precipitation_amount} ${locationForecast.properties.meta.units.precipitation_amount}\n\n")


                Log.e("LOCATIONFORECAST_DATA 12 hour: ", " \n\nDATA-SOURCE LOCATIONFORECAST:  WHEATER DAT IN 12 HOURA\n " +
                        "\nCordinates: ${locationForecast.geometry.coordinates} " +
                        "\nSannsynlighet for nedbør: ${locationForecast.properties.timeseries.firstOrNull()?.data?.next_12_hours?.details?.probability_of_precipitation} % \n\n")

                */

            } catch (e: Exception) {
                // Logg eventuelle feil
                Log.e("ERROR LOCATIONFORECAST_DATA", "feilmelding: ${e.message}")
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
                    Log.e("REPOSITORY-LOCATIONFORECAST: ", " \n\nREPOSOTORY LOCATIONFORECAST: CURRENT WHEATER DATA\n " +
                            "\nCordinates: ${locationForecast.geometry.coordinates} " +
                            "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                            "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                            "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n\n")
                }


            } catch (e: Exception) {
                Log.e("ERROR forecast REPOSITORY", "Feil ved LocatinForecastRepositoryImpl()...", e)
            }
        }
    }
}


/**
@Composable
fun TestLocationForecastViewModel() {
    val forecastViewModel = LocationForecastViewModel()
    val state = forecastViewModel.forecastInfo_UiState.collectAsState()

    val coordinater = Pair("59.9", "10.7")
    LaunchedEffect(coordinater) {
        forecastViewModel.loadForecast(coordinater.first, coordinater.second)
    }
    try {
        while (true) {
        state.value?.let { locationForecast ->
                Log.e(
                    "ViewModel-LOCATIONFORECAST: ",
                    " \n\nVIEW-MODEL LOCATIONFORECAST: CURRENT WHEATER DATA\n " +
                            "\nCordinates: ${locationForecast.geometry.coordinates} " +
                            "\nTemperature: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.air_temperature} ${locationForecast.properties.meta.units.air_temperature}" +
                            "\nUV-Index: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.ultraviolet_index_clear_sky} ${locationForecast.properties.meta.units.ultraviolet_index_clear_sky}" +
                            "\nWind speed: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed} ${locationForecast.properties.meta.units.wind_speed}" +
                            "\nWind direction: ${locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_from_direction} ${locationForecast.properties.meta.units.wind_from_direction}\n\n "
                )
            }
            break
        }

    } catch (e: Exception) {
        // Logg eventuelle feil
        Log.e("ERROR VIEWMODEL FORECAST", "feilmelding: ${e.message}")
        e.printStackTrace()
    }


}
*/


