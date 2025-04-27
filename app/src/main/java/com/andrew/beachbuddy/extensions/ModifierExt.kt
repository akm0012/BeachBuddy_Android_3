package com.andrew.beachbuddy.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun Modifier.previewPlaceholder(modifierInPreview: Modifier): Modifier {
    return if (LocalInspectionMode.current) {
        this.then(modifierInPreview)
    } else {
        this
    }
}