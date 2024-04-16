package no.uio.ifi.in2000.team11.havvarselapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Dataklasse for å representere hvert element i navigasjonsmenyen
 */
data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
/*
@Composable
fun NavigationBar(
    navController: NavController,
    sharedUiState: SharedUiState
) {
    val items = listOf(
        BottomNavigationItem(
            title = "Kart",
            route = "seamap_screen",
            selectedIcon = Icons.Filled.Place,
            unselectedIcon = Icons.Outlined.Place
        ),

        BottomNavigationItem(
            title = "Vær",
            route = "weather_screen",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu
        )
    )
    androidx.compose.material3.NavigationBar(
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
*/
@Composable
fun NavigationBarWithButtons(navController: NavController) {
    Column (modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                navController.navigate("seamap_screen")
            }) {
                Text("Kart")
            }
            Button(onClick = {
                navController.navigate("weather_screen")
            }) {
                Text("Vær")
            }
        }
    }
}
