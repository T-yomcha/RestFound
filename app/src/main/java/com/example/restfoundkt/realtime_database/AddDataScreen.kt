import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restfoundkt.navigation.Screens
import com.example.restfoundkt.realtime_database.Contact
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


fun addContact(contact: Contact) {
    val database: DatabaseReference = Firebase.database.reference
    val key = database.child("contacts").push().key ?: return
    val newContact = Contact(key, contact.name, contact.address, contact.phone, contact.notes)
    database.child("contacts").child(key).setValue(newContact)
}

@Composable
fun ContactForm(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Адрес") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Контакты") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Заметки") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isNotBlank() && address.isNotBlank() && phone.isNotBlank() && notes.isNotBlank()) {
                    val contact = Contact("", name, address, phone, notes)
                    addContact(contact)
                    name = ""
                    address = ""
                    phone = ""
                    notes = ""
                    Toast.makeText(context, "Save", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_LONG).show()
                }
            }
        ) {
            Text("Добавить запись")
        }
        IconButton(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally),
            onClick = {
                navController.navigate(Screens.ReadDataScreen.route)
            })
        {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Cancel")
        }
    }
}