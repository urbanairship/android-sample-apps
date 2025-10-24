package com.airship.sample.debug

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.airship.sample.databinding.FragmentDebugBinding
import com.urbanairship.debug.DebugFragment

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
