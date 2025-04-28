package com.andrew.beachbuddy.ui.specific.beachconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.enums.BeachConditionItemType.CLOUD_COVERAGE_PERCENT
import com.andrew.beachbuddy.enums.BeachConditionItemType.JELLY_FISH
import com.andrew.beachbuddy.enums.BeachConditionItemType.RESPIRATORY_IRRITATION
import com.andrew.beachbuddy.enums.BeachConditionItemType.SURF
import com.andrew.beachbuddy.enums.BeachConditionItemType.TIME_UPDATED
import com.andrew.beachbuddy.enums.BeachConditionItemType.WIND
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun BeachConditionComposable(
    weatherDM: WeatherDM,
    modifier: Modifier = Modifier
) {
    BeachBuddyCard(modifier = modifier.fillMaxHeight()) {
        Column {
            BeachConditionsItem(
                BeachConditionUiState(itemType = CLOUD_COVERAGE_PERCENT, weatherDM = weatherDM),
                showBottomDivider = true,
                modifier = Modifier.weight(1f)
            )
            BeachConditionsItem(
                BeachConditionUiState(itemType = WIND, weatherDM = weatherDM),
                showBottomDivider = true,
                modifier = Modifier.weight(1f)
            )
            BeachConditionsItem(
                BeachConditionUiState(itemType = RESPIRATORY_IRRITATION, weatherDM = weatherDM),
                showBottomDivider = true,
                modifier = Modifier.weight(1f)
            )
            BeachConditionsItem(
                BeachConditionUiState(itemType = SURF, weatherDM = weatherDM),
                showBottomDivider = true,
                modifier = Modifier.weight(1f)
            )
            BeachConditionsItem(
                BeachConditionUiState(itemType = JELLY_FISH, weatherDM = weatherDM),
                showBottomDivider = true,
                modifier = Modifier.weight(1f)
            )
            BeachConditionsItem(
                BeachConditionUiState(itemType = TIME_UPDATED, weatherDM = weatherDM),
                showBottomDivider = false,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun BeachConditionComposablePreview() {
    BeachBuddyTheme {
        BeachConditionComposable(
            weatherDM = WeatherDM(
                currentWeather = CurrentWeather(),
                beachConditions = BeachConditions(),
                uvInfo = CurrentUvInfo(),
                locality = null
            )
        )
    }
}