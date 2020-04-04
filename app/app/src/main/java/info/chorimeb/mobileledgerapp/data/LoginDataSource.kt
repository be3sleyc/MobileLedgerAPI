package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.User
import info.chorimeb.mobileledgerapp.network.NetworkMethods
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val network = NetworkMethods()

    fun login(username: String, password: String): Result<User> {

        return try {

            val putBody = JSONObject("{email:$username,password:$password}").toString()
            println(putBody)

            val url = "http://192.168.1.7/api/users/login"

            val req = network.putRequest(url, putBody, null)
            network.sendRequest(req)


            val user = User(
                1,
                "Jane",
                "Doe",
                "Jane.Doe@gmail.com",
                LocalDateTime.parse(
                    "2020-03-22 03:31:01",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ),
                "abc12345678"
            )
            Result.Success(user)

        } catch (e: Throwable) {
            println(e)
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout(token: String?) {
        // TODO: revoke authentication
    }
}



