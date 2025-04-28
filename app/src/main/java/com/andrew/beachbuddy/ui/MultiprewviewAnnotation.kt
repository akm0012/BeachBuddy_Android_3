package com.andrew.beachbuddy.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Dark Mode",
    group = "UI mode",
    widthDp = 412,
//    heightDp = 915,
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//    showBackground = true
)
@Preview(
    name = "Light Mode",
    group = "UI mode",
    widthDp = 412,
//    heightDp = 915,
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkLightPhonePreviews

@Preview(
    name = "Dark Mode",
    group = "UI mode",
    widthDp = 1280,
    heightDp = 800,
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//    showBackground = true
)
@Preview(
    name = "Light Mode",
    group = "UI mode",
    widthDp = 1280,
    heightDp = 800,
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkLightTabletPreviews