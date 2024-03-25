package no.uio.ifi.in2000.team11.havvarselapp.ui.weather

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
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

    val hovedbildet = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)
    val ikonTemp = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_175)
    val ikonTemp2 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_165)
    val klokke = ImageVector.vectorResource(id = R.drawable.clock)
    val veerikon = ImageVector.vectorResource(id = R.drawable.weather1)
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
    val uv3 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_194)

    val radColor1: Color = Color(235, 238, 255, 255)
    val radColor2: Color = Color(218, 222, 255, 255)
    val radColor3: Color = Color(161, 170, 219, 255)
    val kantLinje: Color = Color(134, 145, 205, 255)




    val PoppinsLight = FontFamily( Font(R.font.poppins_light, FontWeight.W400))
    val NatoSansJP = FontFamily( Font(R.font.notosansjp_variablefont_wght, FontWeight.W400))
    val PoppinsExtralight = FontFamily( Font(R.font.poppins_extralight, FontWeight.W400))
    val PoppinsRegular = FontFamily( Font(R.font.poppins_regular, FontWeight.W400))
    val ojujuRegular = FontFamily( Font(R.font.ojuju_regular, FontWeight.W400))
    val ojujumedium = FontFamily( Font(R.font.ojuju_medium, FontWeight.W400))
    val ojuju = FontFamily( Font(R.font.ojuju_variablefont_wght, FontWeight.W400))
    val truculentaRegular = FontFamily( Font(R.font.truculenta_regular, FontWeight.W400))
    val truculentaLight = FontFamily( Font(R.font.truculenta_semiexpanded_light, FontWeight.W400))
    val truculentaMedium = FontFamily( Font(R.font.truculenta_semiexpanded_medium, FontWeight.W400))
    val truculenta3 = FontFamily( Font(R.font.truculenta_semiexpanded_regular, FontWeight.W400))
    val truculenta4 = FontFamily( Font(R.font.truculenta_60pt_semicondensed_regular, FontWeight.W400))

    var fonts = arrayOf(PoppinsLight, NatoSansJP, PoppinsExtralight, PoppinsRegular, ojujuRegular, ojujumedium, ojuju, truculentaRegular, truculentaLight, truculentaMedium, truculenta3, truculenta4 )
    // FOR Å ENDRE FONT PÅ ALT ENDRE BARE DENNE VARIABELEN !!
    val fontBrukt = fonts[8] // fra 0 - 11



    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

    )

    {
        // Oppretter en kolonne som inneholder teksten for stedets navn, værikon,
        // og en kort oversikt over værdata
        Text(
            text = "Oslo", textAlign = TextAlign.Center,
            fontSize = 35.sp,
            fontFamily = fonts[1],
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top = 20.dp)
        )

        Image(
            imageVector = hovedbildet, contentDescription = "image",
            Modifier
                .size(200.dp)
                .padding(20.dp)
        )

        Card(modifier = Modifier.padding(2.dp).border(width = 1.dp, color = radColor2, shape = RoundedCornerShape(8.dp))
        ) // .border(BorderStroke(6.dp, parseColorString(partyInfo.color)), CircleShape)

        {

            // IKON ØVERST
            Row(
                modifier = Modifier.fillMaxWidth().background(radColor3).padding(top = 7.dp, bottom = 5.dp),
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
                        imageVector = veerikon, contentDescription = "image",
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





            if (forecastViewModel.forecastInfo_UiState.collectAsState().value != null) {
                // Rad 1
                Row(
                    modifier = Modifier.fillMaxWidth().height(50.dp).background(radColor1),
                    horizontalArrangement = Arrangement.Center)
                {// .border(width = 0.5.dp, color = kantLinje, shape = RoundedCornerShape(2.dp))

                    // tidspunkt
                    Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp)) {
                        Text(
                            text = forecastViewModel.getNorskTime(0),
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            fontFamily = fontBrukt
                        )
                    }

                    // Temnperatur
                    Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp)) {
                        Text(
                            text = forecastViewModel.getTemperature(0),
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = fontBrukt
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
                    Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp)) {
                        Image(
                            imageVector = weatherIkon, contentDescription = "image",
                            Modifier.size(40.dp)
                        )
                    }



                    // Vind-speed
                    Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp)) {
                        Text(
                            text = forecastViewModel.getWindSpeed(0),
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = fontBrukt
                        )
                    }

                    // UV index
                    Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp)) {
                        Text(
                            text = "${forecastViewModel.getUVindex(1)}",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = fontBrukt
                        )
                    }
                }


                var farge: Boolean = false
                for (i in 1..5) {
                    if (farge) {
                        WeatherRow(forecastViewModel, i, radColor1, fontBrukt)
                        farge = false
                    } else {
                        WeatherRow(forecastViewModel, i, radColor2, fontBrukt)
                        farge = true
                    }
                }
            }




        } // rad

    }

}



    // Metoden for å laste inn kortene til alle partiene på hovedsiden
    @SuppressLint("DiscouragedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WeatherRow(forecastViewModel: LocationForecastViewModel, time: Int, rowColor: Color, font: FontFamily) {

    val ikonName = forecastViewModel.getWeatherIcon(time) ?: "fair_day"
    val context = LocalContext.current
    val resId = context.resources.getIdentifier(ikonName, "drawable", context.packageName)

    val weatherIkon: ImageVector = if (resId != 0) {
        ImageVector.vectorResource(id = resId)
    } else {
        ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)
    }
        val borderColor: Color = Color(134, 145, 205, 255)

    // Rad x
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(rowColor),
        horizontalArrangement = Arrangement.Center )
    {// .border(width = 0.5.dp, color = borderColor, shape = RoundedCornerShape(2.dp))
        // tidspunkt
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getNorskTime(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                fontFamily = font ) }

        // Temnperatur
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getTemperature(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = font ) }


        // Ikon for været
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Image(
                imageVector = weatherIkon, contentDescription = "image",
                Modifier.size(40.dp)) }



        // Vind-speed
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = forecastViewModel.getWindSpeed(time),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = font ) }

        // UV index
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = "${forecastViewModel.getUVindex(time)}",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = font ) }


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
