package com.example.clubreservationcalendar.di

import android.content.Context
import com.example.clubreservationcalendar.contacts.data.SqlDelightContactDataSource
import com.example.clubreservationcalendar.contacts.domain.ContactDataSource
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.api.ReservationRepository
import com.example.clubreservationcalendar.core.data.DatabaseDriverFactory
import com.example.clubreservationcalendar.core.data.ImageStorage
import com.example.clubreservationcalendar.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            ),
            imageStorage = ImageStorage(context)
        )
    }
    actual val authRepository: AuthRepository
        get() = AuthRepository()

    actual val reservationRepository: ReservationRepository
        get() = ReservationRepository()
}