package no.uio.ifi.in2000.team11.havvarselapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import no.uio.ifi.in2000.team11.havvarselapp.test.TestGribfilesDataSource
import no.uio.ifi.in2000.team11.havvarselapp.test.TestLocationForecastDataSource
//import no.uio.ifi.in2000.team11.havvarselapp.test.TestLocationForecastViewModel
import no.uio.ifi.in2000.team11.havvarselapp.test.TestLocationRepository
import no.uio.ifi.in2000.team11.havvarselapp.ui.theme.HavvarselAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HavvarselAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
            //TestGribfilesDataSource()
            TestLocationForecastDataSource()
           // TestLocationRepository()
           // TestLocationForecastViewModel()
        }
    }
}















@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HavvarselAppTheme {
        Greeting("Android")
    }
}


