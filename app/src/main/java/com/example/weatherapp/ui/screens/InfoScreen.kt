package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R

@Composable
fun InfoScreen(
    modifier: Modifier,
    navController: NavController,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = if (darkTheme) stringResource(R.string.dark_mode) else stringResource(R.string.light_mode))
            Spacer(modifier = Modifier.width(12.dp))
            Switch(
                checked = darkTheme,
                onCheckedChange = { onThemeChange(it) }
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = stringResource(R.string.developed_title),
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = stringResource(R.string.author_info),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
