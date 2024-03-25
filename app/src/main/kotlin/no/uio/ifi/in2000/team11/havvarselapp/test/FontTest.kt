package no.uio.ifi.in2000.team11.havvarselapp.test

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.uio.ifi.in2000.team11.havvarselapp.R

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FontPreview() {

    // ImageVector for værikonet som skal vises,
    // hentet fra drawable-ressursene
    val imageVector = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_01)
    val ikonTemp2 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_165)
    val klokke = ImageVector.vectorResource(id = R.drawable.clock)
    val ikonVind = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_156)
    val vind1 = ImageVector.vectorResource(id = R.drawable.vind1)
    val vind6 = ImageVector.vectorResource(id = R.drawable.vind6)
    val uv1 = ImageVector.vectorResource(id = R.drawable.uv1)
    val uv3 = ImageVector.vectorResource(id = R.drawable.p1honsftvsnih1nss1kofsciqo4_page_194)
    val radColor1: Color = Color(247, 228, 236)
    val radColor2: Color = Color(230, 213, 217)
    val radColor3: Color = Color(198, 156, 166, 255)
    val veerikon = ImageVector.vectorResource(id = R.drawable.weather1)







    /** notosansjp_
    val poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight(400))
    ) */


    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween)

    {
        // Oppretter en kolonne som inneholder teksten for stedets navn, værikon,
        // og en kort oversikt over værdata
        Text(
            text = "Oslo", textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 20.dp))

        Image(
            imageVector = imageVector, contentDescription = "image",
            Modifier
                .size(100.dp)
                .padding(10.dp))

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

            val PoppinsLight = FontFamily( Font(R.font.poppins_light, FontWeight.W400)) // likte !!
            val NatoSansJP = FontFamily( Font(R.font.notosansjp_variablefont_wght, FontWeight.W400)) // likte
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




            var font = truculenta4
            var farge: Boolean = false
                for (i in 10..18) {
                    if (farge) {
                        WeatherRowTest( i, radColor1, font)
                        farge = false
                    } else {
                        WeatherRowTest( i, radColor2, font  )
                        farge = true
                    }
                }
            }

        } // rad

}



// Metoden for å laste inn kortene til alle partiene på hovedsiden
@SuppressLint("DiscouragedApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherRowTest(time: Int, rowColor: Color, font: FontFamily) {

    val weatherIkon = ImageVector.vectorResource(id = no.uio.ifi.in2000.team11.havvarselapp.R.drawable.fair_day)


    // Rad x
    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp).background(rowColor),
        horizontalArrangement = Arrangement.Center )
    {
        // tidspunkt
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = time.toString(),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = font ) }

        // Temnperatur
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = "17 °C",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = font ) }



        // Ikon for været
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Image(
                imageVector = weatherIkon, contentDescription = "image",
                Modifier.size(40.dp)) }



        // Vind-speed
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = " 5 m/s",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                fontFamily = font ) }

        // UV index
        Column(modifier = Modifier.weight(1f).wrapContentSize().padding(top = 15.dp) ) {
            Text(
                text = " 0.4",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = font ) }


    }


}