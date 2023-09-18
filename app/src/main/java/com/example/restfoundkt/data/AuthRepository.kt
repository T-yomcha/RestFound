package com.example.restfoundkt.data

import com.example.restfoundkt.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}