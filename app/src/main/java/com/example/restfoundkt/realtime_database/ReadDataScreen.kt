package com.example.restfoundkt.realtime_database

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.navigation.NavController
import com.example.restfoundkt.navigation.Screens
import com.google.android.gms.tasks.Task


fun readContacts(): LiveData<List<Contact>> {
    val database: DatabaseReference = Firebase.database.reference
    val contactsRef = database.child("contacts")
    val livaData = MutableLiveData<List<Contact>>()
    contactsRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val contacts = mutableListOf<Contact>()
            for (child in snapshot.children) {
                val contact = child.getValue(Contact::class.java)
                if (contact != null) {
                    contacts.add(contact)
                }
            }
            livaData.value = contacts
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("Realtime database", error.message)
        }
    })
    return livaData
}

@Composable
fun ContactList(navController: NavController) {
    val contacts = readContacts().observeAsState(listOf())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(contacts.value) { contact ->
                ContactCard(contact)
            }
        }
        IconButton(
            onClick = {
                navController.navigate(Screens.AddDataScreen.route)
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
        }
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            onClick = {
                navController.navigate(Screens.MapsScreen.route)
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
    }
}

fun deleteContact(contactId: String): Task<Void> {
    val database: DatabaseReference = Firebase.database.reference
    val contactsRef = database.child("contacts")
    return contactsRef.child(contactId).removeValue()
}

@Composable
fun ContactCard(contact: Contact) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Имя: ${contact.name}")
            Text(text = "Адрес: ${contact.address}")
            Text(text = "Контакты: ${contact.phone}")
            Text(text = "Заметки: ${contact.notes}")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                IconButton(onClick = { deleteContact(contact.id) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Удалить")
                }
            }
        }
    }
}