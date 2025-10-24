package com.airship.sample

import AirshipTheme
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.util.Consumer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.airship.sample.AppRouterViewModel.TopLevelDestination
import com.urbanairship.google.PlayServicesUtils.handleAnyPlayServicesError
import com.urbanairship.google.PlayServicesUtils.isGooglePlayStoreAvailable

/**
 * Main application entry point.
 */
class MainActivity : AppCompatActivity() {
    fun TopLevelDestination.title(): String = when(this) {
        is TopLevelDestination.Home -> "Home"
        is TopLevelDestination.MessageCenter -> "Messages"
        is TopLevelDestination.PreferenceCenter -> "Preferences"
        is TopLevelDestination.Settings -> "Settings"
    }

    fun TopLevelDestination.icon(): ImageVector = when(this) {
        is TopLevelDestination.Home -> Icons.Filled.Home
        is TopLevelDestination.MessageCenter -> Icons.Filled.MailOutline
        is TopLevelDestination.PreferenceCenter -> Icons.Filled.Notifications
        is TopLevelDestination.Settings -> Icons.Filled.Settings
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val appRouter: AppRouterViewModel = viewModel(
                modelClass = AppRouterViewModel::class.java,
                factory = AppRouterViewModel.factory()
            )

            DeeplinkHandler.shared.setAppRouter(appRouter)

            val activeTab = appRouter.selectedTopLevel.collectAsState().value
            val backstack = appRouter.activeBackStack.collectAsState().value

            AirshipTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            TopLevelDestination.entries.forEach { item ->
                                val selected = activeTab == item

                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { appRouter.navigate(item) },
                                    label = { Text(text = item.title()) },
                                    alwaysShowLabel = false,
                                    icon = {
                                        Icon(
                                            imageVector = item.icon(),
                                            contentDescription = item.title()
                                        )
                                    }
                                )

                            }
                        }
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                    ) {
                        NavDisplay(
                            backStack = backstack,
                            onBack = { appRouter.pop() },
                            entryDecorators = listOf(
                                // Add the default decorators for managing scenes and saving state
                                rememberSaveableStateHolderNavEntryDecorator(),
                                // Then add the view model store decorator
                                rememberViewModelStoreNavEntryDecorator()
                            ),
                            entryProvider = { key ->
                                appRouter.navigationEntry(key)
                            })
                    }
                }

                // Listen for new intents that may contain deep links
                DisposableEffect(appRouter) {
                    DeeplinkHandler.shared.handle(intent)

                    val listener = Consumer<Intent> {
                        DeeplinkHandler.shared.handle(it)
                    }

                    addOnNewIntentListener(listener)
                    onDispose { removeOnNewIntentListener(listener) }
                }
            }
        }
    }

    public override fun onResume() {
        super.onResume()

        // Handle any Google Play services errors
        if (isGooglePlayStoreAvailable(this)) {
            handleAnyPlayServicesError(this)
        }
    }
}
