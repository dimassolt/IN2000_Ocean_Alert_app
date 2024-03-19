package no.uio.ifi.in2000.team11.havvarselapp.ui.metalert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.uio.ifi.in2000.team11.havvarselapp.model.alert.MetAlert

@Composable
fun CurrentLocationAlert(
    simpleViewModel: SimpleViewModel = viewModel()
) {
    // TODO will be used with current location, "oslo" for now
    var currentLocation: String = "oslo"

    // Observe the UI state object from the ViewModel
    val appUiState: AppUiState by simpleViewModel.appUiState.collectAsState()

// Bruker funksjonen for å filtrere 'allMetAlert' listen basert på 'areal' feltet.
// Funksjon som sjekker om en streng inneholder ordet "oslo" i en case-insensitive måte.
    fun String.containsIgnoreCase(other: String): Boolean {
        return this.contains(other, ignoreCase = true)
    }
// Denne listen vil bare inneholde elementer hvor 'areal' har ordet "oslo".
    val filteredMetAlerts = appUiState.allMetAlerts.filter {
        it.area.containsIgnoreCase(currentLocation)
    }

    if(filteredMetAlerts.size == 0){
        Column {
            Text(
                text = "Ingen farevarsler i \n\n${currentLocation} området!",
                fontSize = 35.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .paddingFromBaseline(50.dp, 10.dp)
            )
        }
    }
    else {

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                // Title
                Text(
                    text = "Farevarsler",
                    fontSize = 35.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .paddingFromBaseline(50.dp, 10.dp)
                )

                // Display all parties
                LazyVerticalGrid(
                    modifier = Modifier.padding(innerPadding),
                    columns = GridCells.Fixed(1)
                ) {
                    items(
                        count = filteredMetAlerts.size,
                        key = { index -> filteredMetAlerts[index].id }
                    ) { index ->
                        MetAlertCardCurrent(metAlert = filteredMetAlerts[index])
                    }
                }
            }
        }
    }
}


@Composable
fun MetAlertCardCurrent(metAlert: MetAlert) {
    Card(
        modifier = Modifier
            .clipToBounds()
            .padding(16.dp)
    ) {
        Text(
            text = metAlert.area,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = metAlert.awarenessLevel.toString(),
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Magenta
        )
        Text(
            text = metAlert.description,
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = metAlert.instruction,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Blue
        )
    }
}