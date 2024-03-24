package no.uio.ifi.in2000.team11.havvarselapp.ui.map

import android.content.Context
import android.location.Address
import androidx.compose.foundation.layout.Box
import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.UrlTileProvider
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.TileOverlay
import com.google.maps.android.compose.rememberCameraPositionState
import no.uio.ifi.in2000.team11.havvarselapp.R
import java.net.URL
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
import androidx.compose.runtime.*
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalFocusManager
import com.google.maps.android.compose.CameraPositionState
import java.io.IOException



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SeaMap() {
    val textState = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // her trengs 'context' for å kunne hente utseende av kartet
    val context = LocalContext.current

    // sjekk om brukeren har gitt tilgang til å hente posisjon
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted
            Log.e("SEA_MAP", "Jippi! Vi har fått posisjostillatelse")
        } else {
            // Handle permission denial
            Log.e("SEA_MAP", "nei")
        }
    }

    LaunchedEffect(locationPermissionState) {
        if (!locationPermissionState.status.isGranted && locationPermissionState.status.shouldShowRationale) {
            // Show rationale if needed
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

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
            isMyLocationEnabled = true,
        )
    ) {
        TileOverlay(
            tileProvider = object : UrlTileProvider(256, 256) {
                override fun getTileUrl(x: Int, y: Int, z: Int): URL {
                    return URL("https://t1.openseamap.org/seamark/$z/$x/$y.png")
                }
            }
        )
    }
        // TextField with the value from textState and an event handler to update textState
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(

                value = textState.value,
                onValueChange = { newText ->
                    textState.value = newText
                },
                // Optional parameters to customize the TextField
                label = { Text("Søk her") },

                leadingIcon = {
                    Icon(
                        Icons.Default.Search, contentDescription = "Search Icon",
                        modifier = Modifier.clickable {
                            if (textState.value.isNotBlank()) {
                                getPosition(textState, context, cameraPositionState)
                            }
                            keyboardController?.hide()
                            focusManager.clearFocus(true)
                        }
                    ) },
                modifier = Modifier.padding(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
                ),
                //closing Text field after presing enter
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                onDone = {
                    if (textState.value.isNotBlank()) {
                        getPosition(textState, context, cameraPositionState)
                    }
                    keyboardController?.hide()
                    focusManager.clearFocus(true)
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
        // Sjekk tilgjengeligheten av nettverkstilgang
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Sjekk om enheten kjører på Android Marshmallow (API 23) eller nyere
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Hent informasjon om nettverkstilkoblingen
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            // Sjekk om enheten har en aktiv internettforbindelse via Wi-Fi eller mobilnett (CELLULAR for mobilnett)
            if (networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                val address_list: List<Address>? = geocoder.getFromLocationName(locationName, 1)
                if (address_list != null && address_list.isNotEmpty()) {
                    val address: Address = address_list[0]
                    val latitude = address.latitude
                    val longitude = address.longitude
                    // TODO: remove or change text later
                    Toast.makeText(
                        context,
                        "Latitude: $latitude, Longitude: $longitude",
                        Toast.LENGTH_SHORT
                    ).show()
                    val searchLocation = LatLng(latitude, longitude)
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(searchLocation, 12f)
                } else {
                    Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Hvis det ikke er noen aktiv internettforbindelse, vis en passende melding
                Toast.makeText(context, "No internet connection available", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
