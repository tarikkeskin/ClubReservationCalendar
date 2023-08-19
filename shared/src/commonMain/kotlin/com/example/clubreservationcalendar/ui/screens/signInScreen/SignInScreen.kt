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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.ui.models.CustomEditTextField
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    authRepository: AuthRepository
) {

    val coroutineScope = rememberCoroutineScope()

    val viewModel = getViewModel(
        key = "sign-in-screen",
        factory = viewModelFactory {
            SignInViewModel(authRepository)
        }
    )
    val state by viewModel.state.collectAsState()

    if (authRepository.isLoggedIn()){ // Main Screen
        Napier.d("Main Screen", tag = "Debug->")
        Button(
            onClick = {
                coroutineScope.launch {
                    //authRepository.delete()
                }
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
                        viewModel.onEvent(SignInEvent.OnEmailChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                CustomEditTextField(
                    value = state.password ?: "",
                    placeholder = "Şifre",
                    error = state.passwordError,
                    onValueChanged = {
                        viewModel.onEvent(SignInEvent.OnPasswordChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(SignInEvent.SignInButtonPressed)
                    }
                ) {
                    Text(text = "Giriş Yap")
                }

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(SignInEvent.SignInWithoutUserButtonPressed)
                    }
                ) {
                    Text(text = "Misafir olarak giriş yap")
                }
            }
        }
    }

}