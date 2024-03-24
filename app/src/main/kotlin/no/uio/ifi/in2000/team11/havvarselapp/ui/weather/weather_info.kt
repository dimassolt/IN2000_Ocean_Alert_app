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
import androidx.compose.foundation.layout.fillMaxSize
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
    val imageVector = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)


    val ikonTemp = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_175)
    val ikonTemp2 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_165)


    val ikonKlokke = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_172)
    val klokke = ImageVector.vectorResource(id = R.drawable.clock)

    val ikonVind = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_156)
    val vind1 = ImageVector.vectorResource(id = R.drawable.vind1)
    val vind2 = ImageVector.vectorResource(id = R.drawable.vind2)
    val vind3 = ImageVector.vectorResource(id = R.drawable.vind3)
    val vind4 = ImageVector.vectorResource(id = R.drawable.vind4)
    val vind5 = ImageVector.vectorResource(id = R.drawable.vind5)
    val vind6 = ImageVector.vectorResource(id = R.drawable.vind6)
    val vind7 = ImageVector.vectorResource(id = R.drawable.vind7)
    val vind9 = ImageVector.vectorResource(id = R.drawable.vind9)

    val uv1 = ImageVector.vectorResource(id = R.drawable.uv1)
    val uv2 = ImageVector.vectorResource(id = R.drawable.uv2)
    val uv3 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_194)

    var radColor1: Color = Color(247, 228, 236)
    var radColor2: Color = Color(230, 213, 217)
    var radColor3: Color = Color(198, 156, 166, 255)

    val værikon = ImageVector.vectorResource(id = R.drawable.weather1)






    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
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

        Card(modifier = Modifier.padding(2.dp))

        {

            // IKON ØVERST
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(radColor3)
                    .padding(top = 7.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.Center, )
                {

                // Klokke ikon
                Column( modifier = Modifier.weight(1f).wrapContentSize() ) {
                    Image(
                        imageVector = klokke, contentDescription = "image",
                        Modifier.size(45.dp).padding(top = 5.dp) )
                }


                // Temnperatur ikon
                Column( modifier = Modifier.weight(1f).wrapContentSize() ) {
                    Image(
                        imageVector = ikonTemp2, contentDescription = "image",
                        Modifier.size(50.dp).padding(2.dp) )
                }

                // Temnperatur ikon
                Column( modifier = Modifier.weight(1f).wrapContentSize() ) {
                    Image(
                        imageVector = værikon, contentDescription = "image",
                        Modifier.size(55.dp))
                }


                // Vind ikon
                Column( modifier = Modifier.weight(1f).wrapContentSize() ) {
                    Image(
                        imageVector = vind1, contentDescription = "image",
                        Modifier.size(50.dp).padding(2.dp) )
                }


                // UV ikon
                Column( modifier = Modifier.weight(1f).wrapContentSize() ) {
                    Image(
                        imageVector = uv1, contentDescription = "image",
                        Modifier.size(45.dp).padding(top = 5.dp) )
                }

                }






            // Rad 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(radColor1),
                horizontalArrangement = Arrangement.Center )
            {

                // tidspunkt
                Column( modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    Text(
                        text = forecastViewModel.getNorskTime(0),
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif ) }

                // Temnperatur
                Column( modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    Text(
                        text = forecastViewModel.getTemperature(1),
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif ) }


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
                Column( modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    Image(
                        imageVector = weatherIkon, contentDescription = "image",
                        Modifier.size(40.dp) ) }


                /**
                // BESKRIVELSE av været
                Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    forecastViewModel.getWeatherIcon(0)?.let {
                        Text(
                            text = it,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif ) } } */


                // Vind-speed
                Column( modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    Text(
                        text = forecastViewModel.getWindSpeed(0),
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif ) }

                // UV index
                Column( modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
                    Text(
                        text = "${forecastViewModel.getUVindex(1)}",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif) }
            }






            var farge: Boolean = false
            for (i in 1..5) {
                if (farge) {
                    WeatherRow(forecastViewModel, i, radColor1)
                    farge = false
                }
                else {
                    WeatherRow(forecastViewModel, i, radColor2)
                    farge = true
                }
            }












        } // rad

    }

}

/**
 *   for (i in 0..6) {
 *                 Box() {
 *                 WeatherCard(forecastViewModel, 0)
 *                 }
 */



    // Metoden for å laste inn kortene til alle partiene på hovedsiden
    @SuppressLint("DiscouragedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WeatherRow(forecastViewModel: LocationForecastViewModel, time: Int, rowColor: Color) {

    val ikonName = forecastViewModel.getWeatherIcon(time) ?: "fair_day"
    val context = LocalContext.current
    val resId = context.resources.getIdentifier(ikonName, "drawable", context.packageName)

    val weatherIkon: ImageVector = if (resId != 0) {
        ImageVector.vectorResource(id = resId)
    } else {
        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)
    }

    // Rad x
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(rowColor),

        horizontalArrangement = Arrangement.Center )
    {
        // tidspunkt
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getNorskTime(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif ) }

        // Temnperatur
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getTemperature(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif ) }






        // Ikon for været
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Image(
                imageVector = weatherIkon, contentDescription = "image",
                Modifier.size(40.dp)) }



        /**
        // BESKRIVELSE av været
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            forecastViewModel.getWeatherIcon(time)?.let {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif )} } */

        // Vind-speed
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getWindSpeed(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif ) }

        // UV index
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = "${forecastViewModel.getUVindex(time)}",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif ) }


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
