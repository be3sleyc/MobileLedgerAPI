package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.db.UserDao
import info.chorimeb.mobileledgerapp.data.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource, val dao: UserDao) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    suspend fun logout() {
        dataSource.logout(user?.token)
        user = null

    }

    suspend fun login(username: String, password: String): Result<User> {
        // handle login
            val result = dataSource.login(username, password)
            if (result is Result.Success) {
                setLoggedInUser(result.data)
            }

            return result
    }

    private suspend fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
