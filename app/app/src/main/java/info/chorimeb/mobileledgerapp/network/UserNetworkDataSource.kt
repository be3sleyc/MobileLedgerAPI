package info.chorimeb.mobileledgerapp.network

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.LoginRequest
import info.chorimeb.mobileledgerapp.data.model.RegisterRequest
import info.chorimeb.mobileledgerapp.data.model.User

interface UserNetworkDataSource {
    val loggedInUser: LiveData<User>
    val userRegistered: LiveData<Boolean>
    val userLoggedOut: LiveData<Boolean>

    suspend fun login(loginRequest: LoginRequest)

    suspend fun registerUser(registerRequest: RegisterRequest)

    suspend fun logout(token: String)
}