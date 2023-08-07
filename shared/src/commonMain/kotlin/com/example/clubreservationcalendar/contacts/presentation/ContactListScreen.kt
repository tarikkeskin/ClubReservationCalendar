package com.example.clubreservationcalendar.contacts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.clubreservationcalendar.contacts.domain.Contact
import com.example.clubreservationcalendar.contacts.presentation.components.AddContactSheet
import com.example.clubreservationcalendar.contacts.presentation.components.ContactDetailSheet
import com.example.clubreservationcalendar.contacts.presentation.components.ContactListItem
import com.example.clubreservationcalendar.contacts.presentation.components.RecentlyAddedContacts
import com.example.clubreservationcalendar.core.api.AuthRepository
import com.example.clubreservationcalendar.core.presentation.ImagePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    state: ContactListState,
    newContact: Contact?,
    authRepository: AuthRepository,
    onEvent: (ContactListEvent) -> Unit,
    imagePicker: ImagePicker
) {
    imagePicker.registerPicker { imageBytes ->
        onEvent(ContactListEvent.OnPhotoPicked(imageBytes))
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add contact"
                )
            }
        }
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.wrapContentHeight(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    RecentlyAddedContacts(
                        contacts = state.recentlyAddedContacts,
                        onClick = {
                            onEvent(ContactListEvent.SelectContact(it))
                        }
                    )
                }

                item {
                    Text(
                        text = "My contacts (${state.contacts.size})",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                items(state.contacts) { contact ->
                    ContactListItem(
                        contact = contact,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onEvent(ContactListEvent.SelectContact(contact))
                            }
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }

    }

    ContactDetailSheet(
        isOpen = state.isSelectedContactSheetOpen,
        selectedContact = state.selectedContact,
        state = state,
        onEvent = onEvent,
    )
    AddContactSheet(
        state = state,
        newContact = newContact,
        isOpen = state.isAddContactSheetOpen,
        onEvent = { event ->
            if(event is ContactListEvent.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )
}

