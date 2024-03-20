package no.uio.ifi.in2000.team11.havvarselapp.ui.weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.uio.ifi.in2000.team11.havvarselapp.R
import no.uio.ifi.in2000.team11.havvarselapp.ui.LocationForecast.LocationForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreen(forecastViewModel: LocationForecastViewModel = viewModel()){
    // ImageVector for værikonet som skal vises,
    // hentet fra drawable-ressursene
    forecastViewModel.loadForecast("59.9", "10.7")
    val weatherInfo = forecastViewModel.forecastInfo_UiState.collectAsState().value
    val imageVector = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween)

        {
            // Oppretter en kolonne som inneholder teksten for stedets navn, værikon,
            // og en kort oversikt over værdata
            Text(text = "Oslo",textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(top=20.dp))

            Image(imageVector = imageVector, contentDescription = "image",
                Modifier
                    .size(200.dp)
                    .padding(20.dp))

        Card(modifier = Modifier.padding(16.dp))

        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Klokken: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${forecastViewModel.getNorskTime(0)} ",

                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    //textAlign = TextAlign.Right
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Temperatur: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${forecastViewModel.getTemperatureNow(0)}  ${forecastViewModel.getTemperatureUnit()}",

                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    //textAlign = TextAlign.Right
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Vind-hastighet: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${forecastViewModel.getWindSpeedNow(0)}  ${forecastViewModel.getWindSpeedUnit()}",

                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    //textAlign = TextAlign.Right
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Vind-retning: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${forecastViewModel.getWindDirection(0)}  ${forecastViewModel.getWindDirectionUnit()}",

                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    //textAlign = TextAlign.Right
                )
            }
        }
    }
}
/**
WeatherScreen Composable funksjon:
- Presenterer brukergrensesnittet for værskjermen, med en sentral kolonne-layout.
- Inkluderer en tekst-widget for stedets navn, et bilde av værikon, og en kort-widget med værdata.
- `imageVector` laster et bilde basert på den angitte drawable-ressursen.
- `Column`-layouten sentrerer innholdet horisontalt og fordeler elementene jevnt.
- `Text`-widget for byens navn har en større skriftstørrelse og polstring på toppen.
- `Image`-widget viser værikonet med passende størrelse og polstring.
- I `Card`-widgeten organiser
*/
