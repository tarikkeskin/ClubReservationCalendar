package com.example.clubreservationcalendar.di

import com.example.clubreservationcalendar.contacts.domain.ContactDataSource
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.api.ReservationRepository
import com.example.clubreservationcalendar.core.data.ImageStorage


expect class AppModule {
    val contactDataSource: ContactDataSource
    val authRepository: AuthRepository
    val reservationRepository: ReservationRepository
    val imageStorage: ImageStorage
}