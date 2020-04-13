package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.db.UserDao
import info.chorimeb.mobileledgerapp.data.model.LoginRequest
import info.chorimeb.mobileledgerapp.data.model.User
import info.chorimeb.mobileledgerapp.network.UserNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class LoginRepositoryImpl(
    private val userDao: UserDao,
    private val userNetworkDataSource: UserNetworkDataSource
) : LoginRepository {

    init {
        userNetworkDataSource.loggedInUser.observeForever { newLoggedInUser ->
            persistLoggedInUser(newLoggedInUser)
        }
        userNetworkDataSource.userLoggedOut.observeForever { newLogOut ->
            if (newLogOut) {
                val user
            }
        }
    }

    override suspend fun login(loginRequest: LoginRequest) {
            userNetworkDataSource.login(loginRequest)
    }

    override suspend fun fetch(email: String): LiveData<User> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUser(email)
        }
    }

    override suspend fun logout(token: String): LiveData<Boolean> {

    }

    private fun persistLoggedInUser(loggedInUser: User) {
        GlobalScope.launch(Dispatchers.IO) {
            userDao.remove(loggedInUser.token)
            userDao.insert(loggedInUser)
        }
    }

    private suspend fun initLoginUser(email: String) {
        if (isLogInNeeded(userDao.getUser(email)))
    }

    private fun isLogInNeeded(lastAccessTime: ZonedDateTime): Boolean {
        val oneWeekAgo = ZonedDateTime.now().minusWeeks(1)
        return lastAccessTime.isBefore(oneWeekAgo)
    }
}