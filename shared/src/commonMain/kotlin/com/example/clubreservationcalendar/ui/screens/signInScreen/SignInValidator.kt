package com.example.clubreservationcalendar.ui.screens.signInScreen

import com.example.clubreservationcalendar.contacts.domain.Contact
import com.example.clubreservationcalendar.contacts.domain.ContactValidator

object SignInValidator {

    fun validateSignIn(email:String?,password:String?): ValidationResult {
        var result = ValidationResult()

        val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if(!emailRegex.matches(email ?: "")) {
            result = result.copy(emailError = "Lütfen geçerli bir e-mail giriniz.")
        }

        if(password.isNullOrBlank()) {
            result = result.copy(passwordError = "Lütfen şifrenizi giriniz.")
        }

        return result
    }

    data class ValidationResult(
        val emailError: String? = null,
        val passwordError: String? = null,
    )
}