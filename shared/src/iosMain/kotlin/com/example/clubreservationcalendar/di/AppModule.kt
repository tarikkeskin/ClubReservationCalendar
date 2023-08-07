package com.example.clubreservationcalendar.di

import com.example.clubreservationcalendar.contacts.data.SqlDelightContactDataSource
import com.example.clubreservationcalendar.contacts.domain.ContactDataSource
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.data.DatabaseDriverFactory
import com.example.clubreservationcalendar.core.data.ImageStorage
import com.example.clubreservationcalendar.database.ContactDatabase

actual class AppModule {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
    actual val authRepository: AuthRepository
        get() = AuthRepository()
}