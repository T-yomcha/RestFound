package com.example.restfoundkt.favorites

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restfoundkt.data_favorites.NameEntity
import java.security.AllPermission

@Composable
fun ListItem(
    item: NameEntity,
    onClick: (NameEntity)->Unit,
    onClickDelete: (NameEntity)->Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onClick(item)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth()
        , verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.restName
            ,modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp)
            )
            IconButton(onClick = {
                    onClickDelete(item)
            }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Del")
            }
        }
    }
}