package com.andrew.beachbuddy

import com.andrew.beachbuddy.ui.specific.sunset.SunsetCountdownUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class SunsetCountdownUiStateTest {

    // Sunset is 8:00pm and sunrise is 8:00am
    private val sunriseTime = 1593864000000L // 2020-07-04T08:00:00 EST
    private val sunsetTime = 1593907200000L  // 2020-07-04T20:00:00 EST
    private val sunriseNextDayTime = 1593950400000L // 2020-07-05T08:00:00 EST
    private val sunsetPrevDay = 1593777600000L // 2020-07-03T08:00:00 EST

//    val viewModel = SunsetCountdownUiState(sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

    //region getBottomLabel()

    @Test
    fun `getBottomLabel when midday`() {

        // It is midday before sunset
        val currentTime = 1593878400000L // 2020-07-04T12:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "SUNSET"

        val actual = viewModel.getBottomLabel()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getBottomLabel when after sunset and before midnight`() {

        // It is 11:00 pm
        val currentTime = 1593918000000L // 2020-07-04T23:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "SUNRISE"

        val actual = viewModel.getBottomLabel()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getBottomLabel when after sunset and after midnight`() {

        // It is 1:00 am
        val currentTime = 1593925200000L // 2020-07-05T01:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "SUNRISE"

        val actual = viewModel.getBottomLabel()

        assertEquals(expeted, actual)
    }

    //endregion

    //region getTimerText()

    @Test
    fun `getTimerText when midday`() {
        // It is midday before sunset
        val currentTime = 1593878400000L // 2020-07-04T12:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "8h 0m"

        val actual = viewModel.getTimerText()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getTimerText when after sunset and before midnight`() {

        // It is 11:00 pm
        val currentTime = 1593918000000L // 2020-07-04T23:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "9h 0m"

        val actual = viewModel.getTimerText()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getTimerText when after sunset and after midnight`() {

        // It is 1:00 am
        val currentTime = 1593925200000L // 2020-07-05T01:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = "7h 0m"

        val actual = viewModel.getTimerText()

        assertEquals(expeted, actual)
    }

    //endregion

    //region getProgressInt()

    @Test
    fun `getProgressInt when midday`() {
        // It is midday before sunset
        val currentTime = 1593878400000L // 2020-07-04T12:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = 0.33f

        val actual = viewModel.getProgress()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getProgressInt when after sunset and before midnight`() {
        // It is 11:00 pm
        val currentTime = 1593918000000L // 2020-07-04T23:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = 0.25f

        val actual = viewModel.getProgress()

        assertEquals(expeted, actual)
    }

    @Test
    fun `getProgressInt when after sunset and after midnight`() {
        // It is 1:00 am
        val currentTime = 1593925200000L // 2020-07-05T01:00:00 EST

        val viewModel = SunsetCountdownUiState(currentTime, sunriseTime, sunsetTime, sunriseNextDayTime, sunsetPrevDay)

        val expeted = 0.42f

        val actual = viewModel.getProgress()

        assertEquals(expeted, actual)
    }

    //endregion
}