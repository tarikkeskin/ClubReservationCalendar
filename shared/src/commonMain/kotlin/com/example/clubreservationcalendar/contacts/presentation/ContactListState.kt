package com.example.clubreservationcalendar.contacts.presentation

import com.example.clubreservationcalendar.contacts.domain.Contact
import dev.gitlive.firebase.auth.FirebaseUser

data class ContactListState(
    val contacts: List<Contact> = emptyList(),
    val currentUser: FirebaseUser? = null,
    val recentlyAddedContacts: List<Contact> = emptyList(),
    val selectedContact: Contact? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)
