package com.example.clubreservationcalendar.contacts.data

import com.example.clubreservationcalendar.contacts.domain.Contact
import com.example.clubreservationcalendar.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let { imageStorage.getImage(it) },
        createdDate = createdAt
    )
}