package no.uio.ifi.in2000.team11.havvarselapp.ui.map

import android.content.Context
import android.location.Address
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import no.uio.ifi.in2000.team11.havvarselapp.R

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import io.ktor.websocket.Frame

import android.location.Geocoder
import android.widget.Toast
import com.google.maps.android.compose.CameraPositionState
import java.io.IOException


@Composable
fun SeaMap() {
    val textState = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    // her trengs 'context' for å kunne hente utseende av kartet
    val context = LocalContext.current

    //TODO slett dette
    var polygonCoordinates: List<LatLng> = listOf(LatLng(6.08989,60.13),LatLng(5.77752,60.6673),LatLng(5.57731,60.9019),LatLng(5.67443,61.1222), LatLng(5.66474,61.4106), LatLng(4.69256,61.4607), LatLng(4.51089,61.0786), LatLng(4.73156,60.5526), LatLng(5.17784,59.5557), LatLng(5.55426,59.7626), LatLng(5.7202,59.8937), LatLng(6.08989,60.13), LatLng(6.08989,60.13))
    polygonCoordinates = polygonCoordinates.map { LatLng(it.longitude, it.latitude) }

    // bruker koordinatene til Oslo som startposisjon
    val oslo = LatLng(59.9, 10.73)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(oslo, 12f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // kart
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                // dette er utseende av kartet, som man finner i filen "mapstyle" i raw-mappen
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle),

                )
        ) {
            // tegner opp et område i kartet //TODO slett dette
            Polygon(
                points = polygonCoordinates,
                visible = true,
                strokeColor = Color.Green,
                fillColor = Color.Transparent,
                strokeJointType = JointType.ROUND
            )
        }

        // TextField with the value from textState and an event handler to update textState
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = textState.value,
                onValueChange = { newText ->
                    textState.value = newText
                },
                // Optional parameters to customize the TextField
                label = { Frame.Text("Enter text here") },
                modifier = Modifier.padding(16.dp),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Hide the keyboard when user presses enter
                        getPosition(textState, context, cameraPositionState)
                        keyboardController?.hide()
                    }
                )
            )
        }
    }
}

fun getPosition(placeName: MutableState<String>, context: Context, cameraPositionState: CameraPositionState){
    val locationName = placeName.value
    val geocoder = Geocoder(context)

    try {
        val addresses: List<Address>? = geocoder.getFromLocationName(locationName, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            val latitude = address.latitude
            val longitude = address.longitude

            // TODO: remove or change text later
            Toast.makeText(
                context,
                "Latitude: $latitude, Longitude: $longitude",
                Toast.LENGTH_SHORT
            ).show()
            val searchLocation = LatLng(latitude, longitude)
            val newPosition = CameraPosition.fromLatLngZoom(searchLocation, 12f)
            cameraPositionState.position = newPosition
        } else {
            Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

