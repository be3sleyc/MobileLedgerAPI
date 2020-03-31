package info.chorimeb.mobileledgerapp.data

import java.io.IOException

class RegistrationDataSource {

    fun register(
        givenname: String,
        surname: String,
        email: String,
        password: String
    ): Result<Boolean> {
        try {
            // TODO: handle registeration process
            // val url = "https://192.168.1.7:3000/api/users/register"

            return Result.Success(true)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error registering", e))
        }
    }
}