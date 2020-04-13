package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData

interface RegistrationRepository {

    suspend fun register(
        givenname: String,
        surname: String,
        email: String,
        password: String
    ): LiveData<Boolean>
}