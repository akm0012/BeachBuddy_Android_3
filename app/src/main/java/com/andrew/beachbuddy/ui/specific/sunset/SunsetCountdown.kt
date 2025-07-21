package com.andrew.beachbuddy.ui.specific.sunset

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.database.model.SunsetInfo
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import kotlinx.coroutines.delay

@Composable
fun SunsetTimerComposable(
    sunsetInfo: SunsetInfo?,
    modifier: Modifier = Modifier,
) {
    // If no sunset info, show a loading screen
    if (sunsetInfo == null) {
        SunsetTimerComposable(modifier = modifier)
        return
    }

    var currentTimeMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTimeMillis = System.currentTimeMillis()
            delay(30 * 1_000) // Update every 30 seconds
        }
    }

    val state = SunsetCountdownUiState(
        currentTime = currentTimeMillis,
        sunrise = sunsetInfo.sunrise,
        sunset = sunsetInfo.sunset,
        sunsetPrevDay = sunsetInfo.sunsetPrevDay,
        sunriseNextDay = sunsetInfo.sunriseNextDay
    )

    SunsetTimerComposable(
        topTitleText = state.getTimerText(),
        middleSubTitleText = state.getSubtitleTime(),
        bottomSubTitleText = state.getBottomLabel(),
        progress = state.getProgress(),
        modifier = modifier
    )
}

@Composable
fun SunsetTimerComposable(
    modifier: Modifier = Modifier,
    topTitleText: String = "--h --m",
    middleSubTitleText: String = "TBD",
    bottomSubTitleText: String = "Loading",
    @FloatRange(from = 0.0, to = 1.0) progress: Float = 1.0f,
) {
    BeachBuddyCard(modifier = modifier) {

        ConstraintLayout(
            modifier = Modifier
                .padding(9.dp)
                .fillMaxHeight()
                .aspectRatio(1f) // Keeps it square
        ) {
            val (circularProgress,
                circularProgressTrack,
                sunsetCountDownText,
                sunsetTimeText,
                bottomTextLabel) = createRefs()

            // Chain the two middle Texts together
            createVerticalChain(sunsetCountDownText, sunsetTimeText, chainStyle = ChainStyle.Packed)

            /*
        We want to keep the bottom of the progress circle from filling. To do this, we rotate
        the circle 225 degrees and treat 75% full as 100% full. So we interpolate the input progress.
        I.E.
            Desired Progress    |   Set Progress
            0%                      0%
            100%                    75%
            50%                     37.5%

        Equation = DesiredProgress * (75/100) = ActualProgress
         */
            val fullTrackMax = 0.75f
            val desiredProgress = progress
            val actualProgress = desiredProgress * (fullTrackMax / 1f)

            // The background color of the track
            CircularProgressIndicator(
                progress = { fullTrackMax },
                color = colorResource(R.color.dashboard_background_color),
                trackColor = Color.Transparent,
                strokeWidth = 10.dp,
                modifier = Modifier
                    .constrainAs(circularProgressTrack) {
                        top.linkTo(circularProgress.top)
                        start.linkTo(circularProgress.start)
                        end.linkTo(circularProgress.end)
                        bottom.linkTo(circularProgress.bottom)
                    }
                    .rotate(225.0f)
                    .fillMaxSize()
            )

            // The actual progress
            CircularProgressIndicator(
                progress = { actualProgress },
                color = colorResource(R.color.colorAccent),
                trackColor = Color.Transparent,
                strokeWidth = 10.dp,
                modifier = Modifier
                    .constrainAs(circularProgress) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .rotate(225.0f)
                    .fillMaxSize()
            )

            Text(
                text = topTitleText,
                fontSize = TextUnit(22f, TextUnitType.Sp),
                color = colorResource(R.color.dashboard_text_dark),
                modifier = Modifier.constrainAs(sunsetCountDownText) {
                    start.linkTo(circularProgress.start)
                    end.linkTo(circularProgress.end)
                    top.linkTo(circularProgress.top)
                    bottom.linkTo(sunsetTimeText.top)
                }
            )

            Text(
                text = middleSubTitleText,
                fontSize = TextUnit(12f, TextUnitType.Sp),
                color = colorResource(R.color.dashboard_text),
                modifier = Modifier.constrainAs(sunsetTimeText) {
                    start.linkTo(circularProgress.start)
                    end.linkTo(circularProgress.end)
                    top.linkTo(sunsetCountDownText.bottom)
                    bottom.linkTo(circularProgress.bottom)
                }
            )

            Text(
                text = bottomSubTitleText,
                fontSize = TextUnit(13f, TextUnitType.Sp),
                color = colorResource(R.color.dashboard_text_dark),
                modifier = Modifier
                    .constrainAs(bottomTextLabel) {
                        start.linkTo(circularProgress.start)
                        end.linkTo(circularProgress.end)
                        bottom.linkTo(circularProgress.bottom)
                    }
                    .padding(bottom = 7.dp)
            )
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun SunsetTimerPreview() {
    BeachBuddyTheme {
        SunsetTimerComposable(
            topTitleText = "11h 19m",
            middleSubTitleText = "8:52 pm",
            bottomSubTitleText = "Sunset",
            progress = 0.75f,
            modifier = Modifier.height(150.dp)
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun SunsetTimerPreviewLoading() {
    BeachBuddyTheme {
        SunsetTimerComposable(
            modifier = Modifier.height(150.dp)
        )
    }
}