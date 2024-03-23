package no.uio.ifi.in2000.team11.havvarselapp.ui.weather

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.uio.ifi.in2000.team11.havvarselapp.R
import no.uio.ifi.in2000.team11.havvarselapp.ui.LocationForecast.LocationForecastViewModel


@SuppressLint("DiscouragedApi")
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreen(forecastViewModel: LocationForecastViewModel = viewModel()) {
    // ImageVector for værikonet som skal vises,
    // hentet fra drawable-ressursene
    forecastViewModel.loadForecast("59.9", "10.7")
    val imageVector =
        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)

    val ikonTemp =
        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.p1honsftvsnih1nss1kofsciqo4_page_175)
    val ikonTemp2 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_165)


    val ikonKlokke =
        ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_172)
    val ikonVind = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_156)
    val ikonUV = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_194)




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )

    {
        // Oppretter en kolonne som inneholder teksten for stedets navn, værikon,
        // og en kort oversikt over værdata
        Text(
            text = "Oslo", textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        Image(
            imageVector = imageVector, contentDescription = "image",
            Modifier
                .size(200.dp)
                .padding(20.dp)
        )

        Card(modifier = Modifier.padding(6.dp))

        {

            // IKON ØVERST
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 7.dp, bottom = 3.dp),
                    horizontalArrangement = Arrangement.Center,
                )
                {

                    // Klokke ikon
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    ) {
                        Image(
                            imageVector = ikonKlokke, contentDescription = "image",
                            Modifier
                                .size(45.dp)
                                .padding(2.dp)
                        )
                    }


                    // Temnperatur ikon
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    ) {
                        Image(
                            imageVector = ikonTemp2, contentDescription = "image",
                            Modifier
                                .size(50.dp)
                                .padding(2.dp)
                        )
                    }


                    // Vind ikon
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    ) {
                        Image(
                            imageVector = ikonVind, contentDescription = "image",
                            Modifier
                                .size(55.dp)
                                .padding(2.dp)
                        )
                    }


                    // UV ikon
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Image(
                            imageVector = ikonUV, contentDescription = "image",
                            Modifier
                                .size(50.dp)
                                .padding(2.dp)
                        )
                    }

                }
            }


            // Rad 1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                )
                {
                    // tidspunkt
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = forecastViewModel.getNorskTime(0),
                            modifier = Modifier
                                .weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }

                    // Temnperatur 
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {

                        Text(
                            text = forecastViewModel.getTemperature(0),
                            modifier = Modifier
                                .weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }


                    val ikonName = forecastViewModel.getWeatherIcon(0) ?: "fair_day"
                    val context = LocalContext.current
                    val resId =
                        context.resources.getIdentifier(ikonName, "drawable", context.packageName)
                    val weatherIkon: ImageVector = if (resId != 0) {
                        ImageVector.vectorResource(id = resId)

                    } else {
                        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)
                    }

                    // Ikon for været
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Image(
                            imageVector = weatherIkon, contentDescription = "image",
                            Modifier.size(40.dp)
                        )

                    }

                    // BESKRIVELSE av været
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        forecastViewModel.getWeatherIcon(0)?.let {
                            Text(
                                text = it,
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.W600,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                    // Vind-speed
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = forecastViewModel.getWindSpeed(0),
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Serif
                        )

                    }

                    // UV index
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = "${forecastViewModel.getUVindex(0)}",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )

                    }


                }
                /*
                for (j in 1..6) {
                    WeatherCard(forecastViewModel, j)
                } */


            }
            /**
            for (i in 0..6) {
            WeatherCard(forecastViewModel, i)
            } */


        }


    }
}




    // Metoden for å laste inn kortene til alle partiene på hovedsiden
    @SuppressLint("DiscouragedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WeatherCard(forecastViewModel: LocationForecastViewModel, time: Int) {

        val ikonName = forecastViewModel.getWeatherIcon(time) ?: "fair_day"
        val context = LocalContext.current
        val resId = context.resources.getIdentifier(ikonName, "drawable", context.packageName)
        val image: ImageVector = if (resId != 0) {
            ImageVector.vectorResource(id = resId)

        } else {
            ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)
        }

        // Kort objektene
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .heightIn(min = 25.dp, max = 25.dp)
                .border(BorderStroke(2.dp, color = Color.DarkGray)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {


            // Rad 1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                )
                {
                    // tidspunkt
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = forecastViewModel.getNorskTime(time),
                            modifier = Modifier
                                .weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }

                    // Temnperatur
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {

                        Text(
                            text = forecastViewModel.getTemperature(time),
                            modifier = Modifier
                                .weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }


                    val ikonName2 = forecastViewModel.getWeatherIcon(time) ?: "fair_day"
                    val context2 = LocalContext.current
                    val resId2 =
                        context2.resources.getIdentifier(
                            ikonName2,
                            "drawable",
                            context2.packageName
                        )
                    val weatherIkon2: ImageVector = if (resId2 != 0) {
                        ImageVector.vectorResource(id = resId2)

                    } else {
                        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)
                    }

                    // Ikon for været
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Image(
                            imageVector = weatherIkon2, contentDescription = "image",
                            Modifier.size(40.dp)
                        )

                    }

                    // BESKRIVELSE av været
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        forecastViewModel.getWeatherIcon(time)?.let {
                            Text(
                                text = it,
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.W600,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                    // Vind-speed
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = forecastViewModel.getWindSpeed(time),
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Serif
                        )

                    }

                    // UV index
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                    )
                    {
                        Text(
                            text = "${forecastViewModel.getUVindex(time)}",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif
                        )

                    }


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
