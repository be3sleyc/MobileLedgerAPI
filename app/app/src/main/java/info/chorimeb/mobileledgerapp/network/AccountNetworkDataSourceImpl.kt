package info.chorimeb.mobileledgerapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest
import info.chorimeb.mobileledgerapp.utilities.NoConnectivityException

class AccountNetworkDataSourceImpl(private val apiService: ApiService) : AccountNetworkDataSource {
    private val _account = MutableLiveData<Account>()
    override val account: LiveData<Account>
        get() = _account

    private val _accounts = MutableLiveData<ArrayList<Account>>()
    override val accounts: LiveData<ArrayList<Account>>
        get() = _accounts

    private val _accountEdited = MutableLiveData<Boolean>()
    override val accountEdited: LiveData<Boolean>
        get() = _accountEdited

    private val _accountAdded = MutableLiveData<Boolean>()
    override val accountAdded: LiveData<Boolean>
        get() = _accountAdded

    override suspend fun getAccount(token: String, id: Int) {
        try {
            val fetchedAccount = apiService.getAccount(token, id)
            _account.postValue(fetchedAccount)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }


    override suspend fun getAccounts(token: String) {
        try {
            val fetchedAccounts = apiService.getAllAccounts(token)
            _accounts.postValue(fetchedAccounts)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun editAccount(
        token: String,
        id: Int,
        editAccountRequest: EditAccountRequest
    ) {
        try {
            val editedAccount = apiService.editAccount(token, id, editAccountRequest)
            _accountEdited.postValue(editedAccount)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun addAccount(token: String, newAccountRequest: NewAccountRequest) {
        try {
            val addedAccount = apiService.addAccount(token, newAccountRequest)
            _accountAdded.postValue(addedAccount)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}