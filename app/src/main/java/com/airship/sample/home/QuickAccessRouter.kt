package com.airship.sample.home

import androidx.navigation3.runtime.NavEntry
import com.airship.sample.Destination

data object QuickAccess {
    fun restore(value: String): Destination? {
        return when(value) {
            NamedUserScreen.NAME-> NamedUserScreen
            else -> null
        }
    }

    data object NamedUserScreen: Destination {
        const val NAME = "named_user"

        override fun serialize(): String = NAME

        override fun navigationEntry(
            onNavigate: (Destination) -> Unit,
            onPopBackStack: () -> Unit
        ): NavEntry<Destination> {
            return NavEntry(this) {
                NamedUserScreen(
                    onNavigateUp = onPopBackStack
                )
            }
        }
    }
}
