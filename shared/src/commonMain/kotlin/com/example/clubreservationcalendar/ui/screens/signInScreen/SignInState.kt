package com.example.clubreservationcalendar.ui.screens.signInScreen

import dev.gitlive.firebase.auth.FirebaseUser

data class SignInState(
    val currentUser: FirebaseUser? = null,
    val email:String? = null,
    val password:String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
)
