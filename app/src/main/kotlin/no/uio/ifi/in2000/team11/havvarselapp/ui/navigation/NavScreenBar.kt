package no.uio.ifi.in2000.team11.havvarselapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.libraries.places.api.net.PlacesClient

/**
 * Denne skjermen har en navigasjonslinje nederst, og dynamisk innhold
 * som fornyes hver gang brukeren navigerer til en annen skjerm
 */
@Composable
fun NavScreen(
    region: String,
    placesClient: PlacesClient,
    navScreenViewModel: NavScreenViewModel = viewModel()
) {
/*
    val currentLocation: String = region
    // Observe the UI state object from the ViewModel
    val appUiState: AppUiState by navScreenViewModel.appUiState.collectAsState()

    // Bruker funksjonen for å filtrere 'allMetAlert' listen basert på 'areal' feltet.
    // Funksjon som sjekker om en streng inneholder ordet "oslo" i en case-insensitive måte.
    fun String.containsIgnoreCase(other: String): Boolean {
        return this.contains(other, ignoreCase = true)
    }

    val filteredMetAlerts = appUiState.allMetAlerts.filter {
        it.area.containsIgnoreCase(currentLocation)
    }
    var amountOfAlerts = filteredMetAlerts.size

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val items = listOf(
            BottomNavigationItem(
                title = "Kart",
                route = "seamap_screen",
                selectedIcon = Icons.Filled.Place,
                unselectedIcon = Icons.Outlined.Place,
                badgeCount = 0
            ),

            BottomNavigationItem(
                title = "Vær",
                route = "weather_screen",
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Outlined.Menu,
                badgeCount = amountOfAlerts
            ),


            BottomNavigationItem(
                title = "Profil",
                route = "profile_screen",
                selectedIcon = Icons.Filled.Face,
                unselectedIcon = Icons.Outlined.Face,
                badgeCount = 0
            ),

            BottomNavigationItem(
                title = "Farevarsel",
                route = "metalerts_screen",
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Outlined.Menu,
                badgeCount = 0
            )
        )
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        Scaffold(
            bottomBar = {
                NavigationBar(
                    // First value = alpha and later RGB
                    containerColor = Color(0xFF_13_23_2C)
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                //navController.navigate(item.title)
                            },
                            label = { Text(text = item.title) },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != 0) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,

                                        contentDescription = item.title
                                    )
                                }
                            },
                            colors = NavigationBarItemColors(
                                //2F4156
                                selectedIconColor = Color(0xFF_D9_D9_D9),
                                selectedTextColor = Color(0xFF_D9_D9_D9),
                                selectedIndicatorColor = Color(0xFF_2F_41_56),
                                unselectedIconColor = Color(0xFF_D9_D9_D9),
                                unselectedTextColor = Color(0xFF_D9_D9_D9),
                                disabledIconColor = Color(0xFF_13_23_2C),
                                disabledTextColor = Color(0xFF_13_23_2C)
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            // Innholdsområde som endres avhengig av valgt navigasjonselement
            Column(modifier = Modifier.padding(innerPadding)) {

                when (selectedItemIndex) {

                    0 -> SeaMapScreen(placesClient)
                    1 -> WeatherScreen()
                    2 -> Profil()
                    // 3 -> SimpleMetAlertScreen()
                    3 -> CurrentLocationAlert("")
                    else -> SeaMapScreen(placesClient)
                }
            }
        }
    }

 */
}
