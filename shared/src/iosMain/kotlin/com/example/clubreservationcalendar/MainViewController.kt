package com.example.clubreservationcalendar

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.example.clubreservationcalendar.core.presentation.ImagePickerFactory
import com.example.clubreservationcalendar.di.AppModule
import dev.gitlive.firebase.Firebase
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.Foundation.NSData
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    Napier.base(DebugAntilog())

    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        appModule = AppModule(),
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker(),
    )
}