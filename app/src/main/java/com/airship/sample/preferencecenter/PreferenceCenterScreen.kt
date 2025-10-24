package com.airship.sample.preferencecenter

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.urbanairship.preferencecenter.compose.ui.PreferenceCenterScreen
import com.urbanairship.preferencecenter.compose.ui.theme.PreferenceCenterColors
import com.urbanairship.preferencecenter.compose.ui.theme.PreferenceCenterTheme

@Composable
fun PreferenceCenterScreen(identifier: String) {
    val lightColors = PreferenceCenterColors.lightDefaults(
        background = MaterialTheme.colorScheme.surfaceContainer,
        surface = MaterialTheme.colorScheme.surface,
        accent = MaterialTheme.colorScheme.primary,
        divider = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
        error = MaterialTheme.colorScheme.error
    )

    val darkColors = PreferenceCenterColors.darkDefaults(
        background = MaterialTheme.colorScheme.surfaceContainer,
        surface = MaterialTheme.colorScheme.surface,
        accent = MaterialTheme.colorScheme.primary,
        divider = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
        error = MaterialTheme.colorScheme.error
    )

    PreferenceCenterTheme(
        colors = if (isSystemInDarkTheme()) darkColors else lightColors
    ) {
        PreferenceCenterScreen(identifier = identifier)
    }
}
