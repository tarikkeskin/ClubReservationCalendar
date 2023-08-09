package com.example.clubreservationcalendar.ui.screens.mainScreen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.clubreservationcalendar.TabNavigationItem
import com.example.clubreservationcalendar.core.presentation.tabs.FavoritesTab
import com.example.clubreservationcalendar.core.presentation.tabs.HomeTab
import com.example.clubreservationcalendar.core.presentation.tabs.ProfileTab

@OptIn(ExperimentalMaterial3Api::class, ExperimentalVoyagerApi::class)
class MainPageScreen: Screen{

    @Composable
    override fun Content() {
        TabNavigator(
            HomeTab(),
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab(), FavoritesTab(), ProfileTab())
                )
            }
        ) { tabNavigator ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = tabNavigator.current.options.title) }
                    )
                },
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab())
                        TabNavigationItem(FavoritesTab())
                        TabNavigationItem(ProfileTab())
                    }
                }
            )
        }
    }

}
