package com.example.clubreservationcalendar.ui.screens.signInScreen

sealed interface SignInEvent{

    data class OnEmailChanged(val value: String): SignInEvent

    data class OnPasswordChanged(val value: String): SignInEvent

    object SignInButtonPressed: SignInEvent

    object SignInWithoutUserButtonPressed: SignInEvent
}