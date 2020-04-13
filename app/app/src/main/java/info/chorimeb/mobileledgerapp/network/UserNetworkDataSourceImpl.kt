package info.chorimeb.mobileledgerapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.chorimeb.mobileledgerapp.data.model.LoginRequest
import info.chorimeb.mobileledgerapp.data.model.RegisterRequest
import info.chorimeb.mobileledgerapp.data.model.User
import info.chorimeb.mobileledgerapp.utilities.NoConnectivityException

class UserNetworkDataSourceImpl(private val apiService: ApiService) : UserNetworkDataSource {
    private val _loggedInUser = MutableLiveData<User>()
    override val loggedInUser: LiveData<User>
        get() = _loggedInUser

    private val _userRegistered = MutableLiveData<Boolean>()
    override val userRegistered: LiveData<Boolean>
        get() = _userRegistered

    private val _userLoggedOut = MutableLiveData<Boolean>()
    override val userLoggedOut: LiveData<Boolean>
        get() = _userLoggedOut

    override suspend fun login(loginRequest: LoginRequest) {
        try {
            val user = apiService.loginUser(loginRequest)
            _loggedInUser.postValue(user)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun registerUser(registerRequest: RegisterRequest) {
        try {
            val registeredUser = apiService.registerUser(registerRequest)
            _userRegistered.postValue(registeredUser)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun logout(token: String) {
        try {
            val loggedOut = apiService.logoutUser(token)
            _userLoggedOut.postValue(loggedOut)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}