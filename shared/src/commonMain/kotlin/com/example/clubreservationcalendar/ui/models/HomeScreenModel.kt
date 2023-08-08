package com.example.clubreservationcalendar.ui.models

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.api.Reservation
import com.example.clubreservationcalendar.core.api.ReservationRepository
import com.example.clubreservationcalendar.core.api.Resource
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenModel (
    private val authRepository: AuthRepository,
    private val reservationRepository: ReservationRepository,
    ): StateScreenModel<HomeScreenModel.State>(State.Init){

    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val reservationList: List<Reservation>) : State()
    }

    val currentUser: FirebaseUser? get() = authRepository.currentUser
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _reservation = MutableStateFlow<Resource<Boolean>?>(null)
    val reservation: StateFlow<Resource<Boolean>?> = _reservation

    init {
        if (authRepository.currentUser != null) {
            _loginFlow.value = Resource.Success(authRepository.currentUser)
        }
    }

    fun addReservation(
        comment: String,
    ) = coroutineScope.launch {
        _reservation.value = Resource.Loading
        var result = reservationRepository.addReservation(
            comment = comment,
        )
        if (result == Resource.Success(true)) {
            _reservation.value = Resource.Success(true)
        }
    }


}