package no.uio.ifi.in2000.team11.havvarselapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SharedUiState(
    /**
     * The current location, shared between all screens
     */
    var currentLocation: LatLng = LatLng(59.9, 10.73),

    /**
     * Amount of met alerts at this location
     */
    var amountOfAlerts: Int = 0
)

class SharedViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    // private UiState, that ViewModel can modify
    private val _sharedUiState = MutableStateFlow(SharedUiState())

    // public UiState that all screens have access to, cannot be modified
    val sharedUiState: StateFlow<SharedUiState> = _sharedUiState.asStateFlow()

    /**
     * Updates the current location, for the whole app
     */
    fun updateLocation(newLocation: LatLng) {
        _sharedUiState.update { currentState ->
            currentState.copy(currentLocation = newLocation)
        }
    }

    /**
     * Updates the amount of met-alerts at this location
     */
    fun updateAmountOfAlerts(amountOfAlerts: Int) {
        _sharedUiState.update { currentState ->
            currentState.copy(amountOfAlerts = amountOfAlerts)
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}