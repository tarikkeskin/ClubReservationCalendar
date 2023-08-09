package com.example.clubreservationcalendar.core.presentation.tabs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.transitions.SlideTransition
import io.github.aakira.napier.Napier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tab.HomeTabContent() {
    val tabTitle = options.title

    LifecycleEffect(
        onStarted = { Napier.d("Navigator", tag = "Start tab $tabTitle") },
        onDisposed = { Napier.d("Navigator", tag = "Dispose tab $tabTitle") },
    )

    Navigator(BasicNavigationScreen(index = 0)) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                InnerTabNavigation()
                screen.Content()
                Napier.d("Navigator", tag = "Last Event: ${navigator.lastEvent}")
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tab.CalendarTabContent() {
    val tabTitle = options.title

    LifecycleEffect(
        onStarted = { Napier.d("Navigator", tag = "Start tab $tabTitle") },
        onDisposed = { Napier.d("Navigator", tag = "Dispose tab $tabTitle") },
    )

    Navigator(BasicNavigationScreen(index = 0)) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                InnerTabNavigation()
                screen.Content()
                Napier.d("Navigator", tag = "Last Event: ${navigator.lastEvent}")
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tab.ProfileTabContent() {
    val tabTitle = options.title

    LifecycleEffect(
        onStarted = { Napier.d("Navigator", tag = "Start tab $tabTitle") },
        onDisposed = { Napier.d("Navigator", tag = "Dispose tab $tabTitle") },
    )

    Navigator(BasicNavigationScreen(index = 0)) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                InnerTabNavigation()
                screen.Content()
                Napier.d("Navigator", tag = "Last Event: ${navigator.lastEvent}")
            }
        }
    }
}

@Composable
private fun InnerTabNavigation() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        TabNavigationButton(HomeTab())

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(FavoritesTab())

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(ProfileTab())
    }
}

@Composable
private fun RowScope.TabNavigationButton(
    tab: Tab
) {
    val tabNavigator = LocalTabNavigator.current

    Button(
        enabled = tabNavigator.current.key != tab.key,
        onClick = { tabNavigator.current = tab },
        modifier = Modifier.weight(1f)
    ) {
        Text(text = tab.options.title)
    }
}
