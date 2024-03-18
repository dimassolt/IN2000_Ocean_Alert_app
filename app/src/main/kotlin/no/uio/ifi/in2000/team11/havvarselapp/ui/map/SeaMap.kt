package no.uio.ifi.in2000.team11.havvarselapp.ui.map

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import no.uio.ifi.in2000.team11.havvarselapp.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SeaMap() {

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

    // kart
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            // dette er utseende av kartet, som man finner i filen "mapstyle" i raw-mappen
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle),
            isMyLocationEnabled = true,
        )
    )
}