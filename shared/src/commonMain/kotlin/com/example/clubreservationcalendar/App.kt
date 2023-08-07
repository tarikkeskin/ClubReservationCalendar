package com.example.clubreservationcalendar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.clubreservationcalendar.contacts.presentation.ContactListScreen
import com.example.clubreservationcalendar.contacts.presentation.ContactListViewModel
import com.example.clubreservationcalendar.core.presentation.ContactsTheme
import com.example.clubreservationcalendar.core.presentation.ImagePicker
import com.example.clubreservationcalendar.di.AppModule
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import com.example.clubreservationcalendar.basisTesting.BasisTesting
import com.example.clubreservationcalendar.core.presentation.tabs.FavoritesTab
import com.example.clubreservationcalendar.core.presentation.tabs.HomeTab
import com.example.clubreservationcalendar.core.presentation.tabs.ProfileTab
import com.example.clubreservationcalendar.datepickerTesting.DatePickerTesting
import com.example.clubreservationcalendar.pagerTesting.PagerTesting

@OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule,
    imagePicker: ImagePicker
) {
    /*ContactsTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        val viewModel = getViewModel(
            key = "contact-list-screen",
            factory = viewModelFactory {
                ContactListViewModel(appModule.contactDataSource,appModule.authRepository)
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ContactListScreen(
                state = state,
                newContact = viewModel.newContact,
                authRepository = appModule.authRepository,
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker
            )
        }
    }*/
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

/*@OptIn(ExperimentalMaterial3Api::class, ExperimentalVoyagerApi::class)
@Composable
fun Content() {
    TabNavigator(
        HomeTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(HomeTab, FavoritesTab, ProfileTab)
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
                    TabNavigationItem(HomeTab)
                    TabNavigationItem(FavoritesTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}*/

@Composable
internal fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}

private val testingPages = mapOf<String, @Composable () -> Unit>(
    "Basis" to { BasisTesting() },
    "Pager" to { PagerTesting() },
    "DatePicker" to { DatePickerTesting() }
)

/*@Composable
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                var currentPage by remember {
                    mutableStateOf(0)
                }

                TabRow(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    selectedTabIndex = currentPage,
                ) {
                    testingPages.keys.forEachIndexed { index, title ->
                        Box(
                            modifier = Modifier
                                .height(90.dp)
                                .clickable {
                                    currentPage = index
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = title,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                CompositionLocalProvider(
                    LocalBasisEpicCalendarConfig provides DefaultBasisEpicCalendarConfig.copy(
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    )
                ) {
                    testingPages[testingPages.keys.toList()[currentPage]]!!()
                }
            }
        }
    }
}*/
