package com.example.clubreservationcalendar.ui.screens.signInScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clubreservationcalendar.ui.models.CustomEditTextField
import io.github.aakira.napier.Napier


@Composable
fun SignInScreen(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
) {

    if (state.currentUser != null){ // Main Screen
        Napier.d("Main Screen", tag = "Debug->")
        Button(
            onClick = {
                onEvent(SignInEvent.SignInWithoutUserButtonPressed)
            }
        ) {
            Text(text = "Misafir olarak giriş yap")
        }
    }else{ // Sign-In Screen
        Napier.d("Sign-In Screen", tag = "Debug->")

        Box(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            contentAlignment = Alignment.TopStart
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(180.dp))

                CustomEditTextField(
                    value = state.email ?: "",
                    placeholder = "E-mail",
                    error = state.emailError,
                    onValueChanged = {
                        onEvent(SignInEvent.OnEmailChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                CustomEditTextField(
                    value = state.password ?: "",
                    placeholder = "Şifre",
                    error = state.passwordError,
                    onValueChanged = {
                        onEvent(SignInEvent.OnPasswordChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = {
                        onEvent(SignInEvent.SignInButtonPressed)
                    }
                ) {
                    Text(text = "Giriş Yap")
                }

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = {
                        onEvent(SignInEvent.SignInWithoutUserButtonPressed)
                    }
                ) {
                    Text(text = "Misafir olarak giriş yap")
                }
            }
        }
    }

}