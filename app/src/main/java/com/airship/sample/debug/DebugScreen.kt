package com.airship.sample.debug

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.compose.rememberNavController
//import com.urbanairship.debug.DebugFragment
import com.airship.sample.databinding.FragmentDebugBinding
import com.urbanairship.debug.DebugFragment
import com.urbanairship.debug.ui.home.DebugNavHost

@Composable
fun DebugScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { paddingValues ->
        Surface(Modifier.padding(paddingValues)) {
            AndroidViewBinding(FragmentDebugBinding::inflate) {
                val fragment = debugFragmentContainerView.getFragment<DebugFragment>()
            }
        }
    }
}
