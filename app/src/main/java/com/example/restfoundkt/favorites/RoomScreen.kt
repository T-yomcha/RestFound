package com.example.restfoundkt.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun roomScreen(favouritesViewModel: FavouritesViewModel= viewModel(factory = FavouritesViewModel.factory)
) {
    val itemsList=favouritesViewModel.itemsList.collectAsState(initial = emptyList())
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            , verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(value = favouritesViewModel.newRestName.value, onValueChange ={
                favouritesViewModel.newRestName.value=it
            },
                label = {
                    Text(text = "Restaurant name", )
                },
                modifier = Modifier.weight(1f),
                colors=TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
            IconButton(onClick = {
                favouritesViewModel.insertItem()
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add")
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()
        ) {
            items(itemsList.value){item->
                ListItem(
                    item,
                    {
                        favouritesViewModel.nameEntity=it
                        favouritesViewModel.newRestName.value=it.restName
                    },
                    {
                        favouritesViewModel.deleteItem(it)
                    }
                )
            }
        }
    }
}