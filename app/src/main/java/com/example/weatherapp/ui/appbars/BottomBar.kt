package com.example.weatherapp.ui.appbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.R
import com.example.weatherapp.model.TabItem


@Composable
fun BottomBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val tabs = listOf(
        TabItem(label = stringResource(R.string.home), Icons.Filled.Home, route = "weather"),
        TabItem(label = stringResource(R.string.info), Icons.Filled.Info, route = "info"),
    )

    NavigationBar {
        tabs.forEach { tab ->
            val selected = tab.route == backStackEntry?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(tab.route) },
                label = { Text(text = tab.label) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label
                    )
                }
            )
        }
    }
}