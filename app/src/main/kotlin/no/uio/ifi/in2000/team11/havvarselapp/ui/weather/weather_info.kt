package no.uio.ifi.in2000.team11.havvarselapp.ui.weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val imageVector = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)

    val ikonTemp = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_175)
    val ikonTemp2 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_165)



    val ikonKlokke = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_172)
    val ikonVind  = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_156)
    val ikonUV  = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_194)



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
            /**
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Klokken: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = forecastViewModel.getNorskTime(1),

                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    //textAlign = TextAlign.Right
                )
            }
            Image(imageVector = ikonKlokke, contentDescription = "image",
            Modifier
            .size(40.dp)
            .padding(5.dp))

             */
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
            ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
                horizontalArrangement = Arrangement.Center,
            )
            {
                Column( modifier = Modifier
                    .weight(1f)
                    .wrapContentSize())  {
                    Image(imageVector = ikonKlokke, contentDescription = "image",
                        Modifier
                            .size(45.dp)
                            .padding(2.dp))
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .wrapContentSize())  {
                    Image(imageVector = ikonTemp2, contentDescription = "image",
                        Modifier
                            .size(50.dp)
                            .padding(2.dp))
                }
                Column( modifier = Modifier
                    .weight(1f)
                    .wrapContentSize())  {
                    Image(imageVector = ikonVind, contentDescription = "image",
                        Modifier
                            .size(55.dp)
                            .padding(2.dp))
                }

                Column(modifier = Modifier
                    .weight(1f)
                    .wrapContentSize())  {
                    Image(imageVector = ikonUV, contentDescription = "image",
                        Modifier
                            .size(50.dp)
                            .padding(2.dp))
                }

            } }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)){
                Text(text = "Temperatur: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = forecastViewModel.getTemperature(1),

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
                Text(text = forecastViewModel.getWindSpeed(1),

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
                Text(text = forecastViewModel.getWindDirection(1),

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
                Text(text = "UV-index: ",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${forecastViewModel.getUVindex(0)}",

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
