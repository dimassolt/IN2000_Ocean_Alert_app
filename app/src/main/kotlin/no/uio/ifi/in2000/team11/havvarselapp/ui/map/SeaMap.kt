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
import androidx.compose.runtime.*
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.LocalFocusManager
import com.google.maps.android.compose.CameraPositionState
import java.io.IOException
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener



@Composable
fun SeaMap() {
    val textState = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // her trengs 'context' for å kunne hente utseende av kartet
    val context = LocalContext.current

    //TODO slett dette
    var polygonCoordinates: List<LatLng> = listOf(
        LatLng(6.08989,60.13),
        LatLng(5.77752,60.6673),
        LatLng(5.57731,60.9019),
        LatLng(5.67443,61.1222),
        LatLng(5.66474,61.4106),
        LatLng(4.69256,61.4607),
        LatLng(4.51089,61.0786),
        LatLng(4.73156,60.5526),
        LatLng(5.17784,59.5557),
        LatLng(5.55426,59.7626),
        LatLng(5.7202,59.8937),
        LatLng(6.08989,60.13),
        LatLng(6.08989,60.13))
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





//@Composable
//fun AutocompleteSearch(
//    context: Context,
//    onPlaceSelected: (LatLng) -> Unit
//) {
//    var query by remember { mutableStateOf("") }
//
//    val autocompleteFragment = remember(context) {
//        AutocompleteSupportFragment().apply {
//            setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))
//            setOnPlaceSelectedListener(object : PlaceSelectionListener {
//                override fun onPlaceSelected(place: Place) {
//                    onPlaceSelected(place.latLng!!)
//                }
//
//                override fun onError(status: com.google.android.gms.common.api.Status) {
//                    // Handle the error TODO:
//                    println("ERROR...")
//                }
//            })
//        }
//    }
////
////    BackHandler {
////        if (autocompleteFragment.view != null) {
////            (autocompleteFragment.view?.parent as? FrameLayout)?.removeAllViews()
////        }
////    }
////
////    val placesClient = remember {
////        Places.createClient(context)
////    }
//
//    OutlinedTextField(
//        value = query,
//        onValueChange = {
//            query = it
//            autocompleteFragment.setText(it)
//        },
//        label = { Text("Search here") },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    )
//
////    AndroidView({ autocompleteFragment.requireView() })
//}
