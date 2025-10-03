package com.airship.sample.debug

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//import com.urbanairship.debug.DebugFragment
//import com.airship.sample.databinding.FragmentDebugBinding

@Composable
fun DebugScreen(
    modifier: Modifier
) {
    Scaffold(modifier = modifier) { paddingValues ->
        Surface(Modifier.padding(paddingValues)) {
            Text("Will be added in v20")
//            AndroidViewBinding(FragmentDebugBinding::inflate) {
//                val fragment = debugFragmentContainerView.getFragment<DebugFragment>()
//            }
        }
    }
}
