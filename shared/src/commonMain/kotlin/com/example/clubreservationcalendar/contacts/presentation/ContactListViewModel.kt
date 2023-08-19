package com.example.clubreservationcalendar.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.clubreservationcalendar.contacts.domain.Contact
import com.example.clubreservationcalendar.contacts.domain.ContactDataSource
import com.example.clubreservationcalendar.contacts.domain.ContactValidator
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.api.ReservationRepository
import com.example.clubreservationcalendar.core.api.Resource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactDataSource: ContactDataSource,
    private val authRepository: AuthRepository,
    private val reservationRepository: ReservationRepository
): ViewModel() {

    private val _state = MutableStateFlow(ContactListState())
    val state = combine( // takes multiple flows and when any of them change it gives most up to date value
        _state,
        contactDataSource.getContacts(),
        contactDataSource.getRecentContacts(20)
    ) { state, contacts, recentContacts ->
        state.copy(
            contacts = contacts,
            recentlyAddedContacts = recentContacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ContactListState())

    var newContact: Contact? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {
        when(event) {
            ContactListEvent.DeleteContact -> {
                viewModelScope.launch {
                    _state.value.selectedContact?.id?.let { id ->
                        _state.update { it.copy(
                            isSelectedContactSheetOpen = false
                        ) }
                        contactDataSource.deleteContact(id)
                        delay(300L) // Animation delay
                        _state.update { it.copy(
                            selectedContact = null
                        ) }
                    }
                }
            }
            ContactListEvent.DismissContact -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isSelectedContactSheetOpen = false,
                        isAddContactSheetOpen = false,
                        firstNameError = null,
                        lastNameError = null,
                        emailError = null,
                        phoneNumberError = null
                    ) }
                    delay(300L) // Animation delay
                    newContact = null
                    _state.update { it.copy(
                        selectedContact = null
                    ) }
                }
            }
            is ContactListEvent.EditContact -> {
                _state.update { it.copy(
                    selectedContact = null,
                    isAddContactSheetOpen = true,
                    isSelectedContactSheetOpen = false
                ) }
                newContact = event.contact
            }
            ContactListEvent.OnAddNewContactClick -> {
                _state.update { it.copy(
                    isAddContactSheetOpen = true
                ) }
                newContact = Contact(
                    id = null,
                    firstName = "",
                    lastName = "",
                    email = "",
                    phoneNumber = "",
                    photoBytes = null,
                    createdDate = null
                )
            }
            is ContactListEvent.OnEmailChanged -> {
                newContact = newContact?.copy(
                    email = event.value
                )
            }
            is ContactListEvent.OnFirstNameChanged -> {
                newContact = newContact?.copy(
                    firstName = event.value
                )
            }
            is ContactListEvent.OnLastNameChanged -> {
                newContact = newContact?.copy(
                    lastName = event.value
                )
            }
            is ContactListEvent.OnPhoneNumberChanged -> {
                newContact = newContact?.copy(
                    phoneNumber = event.value
                )
            }
            is ContactListEvent.OnPhotoPicked -> {
                newContact = newContact?.copy(
                    photoBytes = event.bytes
                )
            }
            ContactListEvent.SaveContact -> {
                newContact?.let { contact ->
                    val result = ContactValidator.validateContact(contact)
                    val errors = listOfNotNull(
                        result.firstNameError,
                        result.lastNameError,
                        result.emailError,
                        result.phoneNumberError
                    )

                    if(errors.isEmpty()) {

                        _state.update { it.copy(
                            isAddContactSheetOpen = false,
                            firstNameError = null,
                            lastNameError = null,
                            emailError = null,
                            phoneNumberError = null
                        ) }
                        viewModelScope.launch {
                            contactDataSource.insertContact(contact)
                            delay(300L) // Animation delay
                            newContact = null
                        }

                        /*viewModelScope.launch {
                            //contactDataSource.insertContact(contact)

                            when(val resource = authRepository.signIn(contact.email,contact.firstName)){
                                is Resource.Success -> {
                                    _state.update {
                                        it.copy(
                                            isAddContactSheetOpen = false,
                                            firstNameError = null,
                                            lastNameError = null,
                                            emailError = null,
                                            phoneNumberError = null,
                                            currentUser = resource.result
                                        )
                                    }
                                }
                                is Resource.Failure ->{
                                    _state.update {
                                        it.copy(
                                            isAddContactSheetOpen = false,
                                            firstNameError = null,
                                            lastNameError = null,
                                            emailError = null,
                                            phoneNumberError = null,
                                            currentUser = null
                                        )
                                    }
                                }
                                is Resource.Loading -> {

                                }


                            }
                            delay(300L) // Animation delay
                            newContact = null
                        }*/
                    } else {
                        _state.update { it.copy(
                            firstNameError = result.firstNameError,
                            lastNameError = result.lastNameError,
                            emailError = result.emailError,
                            phoneNumberError = result.phoneNumberError
                        ) }
                    }
                }
            }
            is ContactListEvent.SelectContact -> {
                _state.update { it.copy(
                    selectedContact = event.contact,
                    isSelectedContactSheetOpen = true
                ) }
            }
            else -> Unit
        }
    }
}