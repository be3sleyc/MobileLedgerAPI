package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData

class RegistrationRepositoryImpl : RegistrationRepository {
    override suspend fun register(
        givenname: String,
        surname: String,
        email: String,
        password: String
    ): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}