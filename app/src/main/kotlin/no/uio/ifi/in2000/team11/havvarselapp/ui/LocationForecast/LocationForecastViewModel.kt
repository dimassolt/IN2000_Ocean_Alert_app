package no.uio.ifi.in2000.team11.havvarselapp.ui.LocationForecast

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.team11.havvarselapp.data.locationForecast.LocatinForecastRepositoryImpl
import no.uio.ifi.in2000.team11.havvarselapp.model.locationForecast.LocationForecast
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class IsAPiCalled( // for å unngå for mange API kall
    var iscalled: Boolean = false
)


class LocationForecastViewModel(
    private val repository: LocatinForecastRepositoryImpl = LocatinForecastRepositoryImpl()
) : ViewModel() {
    private val _forecastInfo_UiState = MutableStateFlow<LocationForecast?>(null)
    val forecastInfo_UiState: StateFlow<LocationForecast?> = _forecastInfo_UiState.asStateFlow()
    var isAPiCalled by mutableStateOf(IsAPiCalled())


    init {
        loadForecast("59.911491", "10.757933") // starter opp med Latitude og longitude tilsvarende Oslo
    }

    fun loadForecast(lat: String, lon: String) {
        if (isAPiCalled.iscalled) {
            return;
        }
        else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    isAPiCalled.iscalled = true
                    val forecast = repository.getLocationForecast(lat, lon)
                    _forecastInfo_UiState.update { forecast }
                    Log.e("VIEWMODEL", "API-kall")

                } catch (e: Exception) {
                    Log.e(
                        "ERROR ForeCast ViewModel",
                        "error in LocationForecastViewModel()loadForecast() ",
                        e
                    )
                }
            }
        }
    }

    /**
     * Denne returnerer dato og tid typ: '20 March 2024 16:00' i NORSK TID
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun konvertDateAndTime(time: Int): String {
        val currentForecast = _forecastInfo_UiState.value
        val tid_String: String = "${currentForecast?.properties?.timeseries?.get(time)?.time}"
        var parsedDate = ZonedDateTime.parse(tid_String)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm")
        return parsedDate.withZoneSameInstant(ZoneId.of("Europe/Oslo")).format(formatter)
    }

    /**
     * Denne returnerer norsk tid - typ: '16:00'
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNorskTime(time: Int): String {
        val currentForecast = _forecastInfo_UiState.value
        val tid_String: String = "${currentForecast?.properties?.timeseries?.get(time)?.time}"
        var parsedDate = ZonedDateTime.parse(tid_String)
        return parsedDate.withZoneSameInstant(ZoneId.of("Europe/Oslo")).hour.toString()
    }

    fun getCordinates(): List<Double>? {
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.geometry?.coordinates
    }

    fun getTemperatureNow(time: Int): Double? { // grader er i celsius
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.timeseries?.get(time)?.data?.instant?.details?.air_temperature
    }

    fun getTemperatureUnit(): String? { // grader er i celsius
        val currentForecast = _forecastInfo_UiState.value
        return if (currentForecast?.properties?.meta?.units?.air_temperature == "celsius") "°C" else currentForecast?.properties?.meta?.units?.air_temperature
    }


    fun getWindSpeedNow(time: Int): Double? { // UV-indexen under klare himmelforhold
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.timeseries?.get(time)?.data?.instant?.details?.wind_speed
    }

    fun getWindSpeedUnit(): String? { // grader er i celsius
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.meta?.units?.wind_speed
    }


    fun getWindDirection(time: Int): Double? { // UV-indexen under klare himmelforhold
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.timeseries?.get(time)?.data?.instant?.details?.wind_from_direction
    }

    fun getWindDirectionUnit(): String? { // grader er i celsius
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.meta?.units?.wind_from_direction
    }

    fun probability_of_precipitation_12hours(): Double? { // Sannsynlighet for nedbør om 12 timer
        val currentForecast = _forecastInfo_UiState.value
        return currentForecast?.properties?.timeseries?.firstOrNull()?.data?.next_12_hours?.details?.probability_of_precipitation
    }




}