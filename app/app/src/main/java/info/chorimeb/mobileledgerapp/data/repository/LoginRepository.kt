package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.LoginRequest
import info.chorimeb.mobileledgerapp.data.model.User


interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest)
    suspend fun fetch(email: String): LiveData<User>
    suspend fun logout(token: String): LiveData<Boolean>
}
