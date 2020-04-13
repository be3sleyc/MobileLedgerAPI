package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.User
import org.json.JSONObject
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<User> {

        return try {

            val putBody = JSONObject("{email:$username,password:$password}").toString()
            println(putBody)

            val url = "/users/login"

            val user = User(
                1,
                "Jane",
                "Doe",
                "Jane.Doe@gmail.com",
                "2020-03-22 03:31:01",
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



