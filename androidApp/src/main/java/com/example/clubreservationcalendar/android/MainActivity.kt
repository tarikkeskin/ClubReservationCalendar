package com.example.clubreservationcalendar.android

import android.app.ActionBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.clubreservationcalendar.App
import com.example.clubreservationcalendar.core.presentation.ImagePickerFactory
import com.example.clubreservationcalendar.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                appModule = AppModule(LocalContext.current.applicationContext),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

/*

@Preview(device = "spec:width=1080px,height=2340px,dpi=480", backgroundColor = 0xFFCA3434)
@Composable
fun DefaultPreview() {
    AddContactSheet(
        state = ContactListState(
            firstNameError= "feeer"
        ),
        newContact = null,
        isOpen = true,
        onEvent = { event ->

        },
    )
}*/
