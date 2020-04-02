package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.LoggedInUser
import info.chorimeb.mobileledgerapp.network.NetworkMethods
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val network = NetworkMethods()

    fun login(username: String, password: String): Result<LoggedInUser> {

        return try {

            val putBody = "{email:$username,password:$password}"

            val url = "https://192.168.1.7/api/users/login"

            val req = network.putRequest(url, putBody)
            network.sendRequest(req)

            val fakeUser = LoggedInUser(
                1,
                "Jane",
                "Doe",
                "Jane.Doe@gmail.com",
                LocalDateTime.parse(
                    "2020-03-22 03:31:01",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ),
                "12345abcde"
            )
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            println(e)
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout(token: String?) {
        // TODO: revoke authentication
    }
}

