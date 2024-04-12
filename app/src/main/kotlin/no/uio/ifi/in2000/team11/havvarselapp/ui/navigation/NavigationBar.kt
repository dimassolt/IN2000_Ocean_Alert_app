package no.uio.ifi.in2000.team11.havvarselapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import no.uio.ifi.in2000.team11.havvarselapp.SharedUiState

@Composable
fun NavigationBar(
    navController: NavController,
    sharedUiState: SharedUiState
) {
    val items = listOf(
        BottomNavigationItem(
            title = "Kart",
            selectedIcon = Icons.Filled.Place,
            unselectedIcon = Icons.Outlined.Place,
            badgeCount = 0
        ),

        BottomNavigationItem(
            title = "Vær",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
            badgeCount = sharedUiState.amountOfAlerts
        ),


        BottomNavigationItem(
            title = "Profil",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
            badgeCount = 0
        ),

        BottomNavigationItem(
            title = "Farevarsel",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
            badgeCount = 0
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
