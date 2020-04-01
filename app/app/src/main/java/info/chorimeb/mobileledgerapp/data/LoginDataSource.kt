package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.LoggedInUser
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            // val url = "https://192.168.1.7:3000/api/users/login"

            val fakeUser = LoggedInUser(1, "Jane", "Doe", "Jane.Doe@gmail.com", LocalDateTime.parse("2020-03-22 03:31:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "12345abcde" )
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }


    fun logout(token: String? ) {
        // TODO: revoke authentication
    }
}

