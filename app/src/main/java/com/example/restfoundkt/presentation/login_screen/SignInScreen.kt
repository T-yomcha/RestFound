package com.example.restfoundkt.presentation.login_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.restfoundkt.navigation.Screens
import com.example.restfoundkt.ui.theme.Black300
import com.example.restfoundkt.ui.theme.Blue300
import com.example.restfoundkt.ui.theme.Orange300
import kotlinx.coroutines.launch

@Composable
fun signInScreen(
    viewModel: SignInViewModel = hiltViewModel(), navController: NavController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isTextClicked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Blue),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Email", color = Color.White)
            },
            textStyle = TextStyle(color=Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Blue),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Password", color = Color.White)
            },
            textStyle = TextStyle(color=Color.White)
        )
        Button(
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 50.dp, end = 50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = "Sign In", color = Color.White, modifier = Modifier.padding(7.dp)
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable(onClick = {
                    navController.navigate(Screens.SignUpScreen.route)
                    isTextClicked = true
                }),

            text = "Already no have an account? Sign up",
            fontWeight = FontWeight.Bold,
            color = if (isTextClicked) Blue300 else Color.Black
        )


        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    navController.navigate(Screens.MapsScreen.route)
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}