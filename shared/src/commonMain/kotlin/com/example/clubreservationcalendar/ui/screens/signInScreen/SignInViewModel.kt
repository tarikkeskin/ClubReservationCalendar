package com.example.clubreservationcalendar.ui.screens.signInScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.clubreservationcalendar.contacts.domain.Contact
import com.example.clubreservationcalendar.contacts.domain.ContactValidator
import com.example.clubreservationcalendar.contacts.presentation.ContactListEvent
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.api.Resource
import dev.gitlive.firebase.auth.FirebaseUser
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    var user: User? by mutableStateOf(null)
        private set

    fun onEvent(event: SignInEvent) {
        when(event) {
            is SignInEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(email = event.value)
                }
            }
            is SignInEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(password = event.value)
                }
            }
            SignInEvent.SignInButtonPressed -> {

                val result = SignInValidator.validateSignIn(state.value.email,state.value.password)
                val errors = listOfNotNull(
                    result.emailError,
                    result.passwordError
                )
                if(errors.isEmpty()) {
                    viewModelScope.launch {
                        when(val resource = authRepository.signIn(state.value.email!!,state.value.email!!)){
                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        currentUser = resource.result,
                                        emailError = null,
                                        passwordError = null,
                                    )
                                }
                                // TODO: Navigate to HomePage

                            }
                            is Resource.Failure ->{
                                _state.update {
                                    it.copy(
                                        currentUser = null,
                                        emailError = null,
                                        passwordError = resource.exception.message,
                                    )
                                }
                            }
                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        currentUser = null,
                                        emailError = null,
                                        passwordError = "Giriş yapılıyor...",
                                    )
                                }
                            }
                        }
                    }
                } else {
                    _state.update { it.copy(
                        emailError = result.emailError,
                        passwordError = result.passwordError
                    ) }
                }


            }

            SignInEvent.SignInWithoutUserButtonPressed -> {

            }
        }
    }

}