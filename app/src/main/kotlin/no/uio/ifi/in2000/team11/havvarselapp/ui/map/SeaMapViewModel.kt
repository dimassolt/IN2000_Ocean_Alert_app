package no.uio.ifi.in2000.team11.havvarselapp.ui.map

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.team11.havvarselapp.R
import no.uio.ifi.in2000.team11.havvarselapp.model.harbor.Harbor
import org.json.JSONArray

data class MapUiState(

    /** Er det en synlig pin på kartet? */
    val markerVisible: Boolean = false,

)

class SeaMapViewModel : ViewModel() {
    // oppretter en privat state flow, denne er mutable og kan derfor endres på
    private val _mapUiState = MutableStateFlow(MapUiState())

    // oppretter en immutable/uforanderlig UiState av typen StateFlow
    val mapUiState: StateFlow<MapUiState> = _mapUiState.asStateFlow()
    // Gjestehavner
    val harborData = MutableLiveData<List<Harbor>>()

    fun placeOrRemoveMarker() {
        // hvis marker allerede er synlig, fjern den
        if (mapUiState.value.markerVisible) {
            _mapUiState.update { currentState ->
                currentState.copy(markerVisible = false)
            }
        } else {
            _mapUiState.update { currentState ->
                currentState.copy(
                    markerVisible = true
                )
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun fetchHarborData(context: Context) {
        GlobalScope.launch {
            val harborList = mutableListOf<Harbor>()
            val inputStream = context.resources.openRawResource(R.raw.guestharbors)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonString = String(buffer)
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                harborList.add(
                    Harbor(
                        item.getInt("id"),
                        item.getString("name"),
                        item.getJSONArray("location").let { arrayOf(it.getDouble(0), it.getDouble(1)) },
                        item.getString("description")
                    )
                )
            }

            harborData.postValue(harborList)
        }
    }
}
