package com.example.clubreservationcalendar.pagerTesting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import epicarchitect.calendar.compose.pager.state.EpicCalendarPagerState
import com.example.clubreservationcalendar.Button
import com.example.clubreservationcalendar.TestingSection

@Composable
fun PagerStateControls(state: EpicCalendarPagerState) {
    TestingSection(
        title = "currentMonth: " + state.currentMonth.year.toString() + " " + state.currentMonth.month.name
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    state.scrollMonths(-1)
                },
                text = "-1 month"
            )

            Button(
                onClick = {
                    state.scrollMonths(1)
                },
                text = "+1 month"
            )

            Button(
                onClick = {
                    state.scrollYears(-1)
                },
                text = "-1 year"
            )

            Button(
                onClick = {
                    state.scrollYears(1)
                },
                text = "+1 year"
            )
        }
    }

}