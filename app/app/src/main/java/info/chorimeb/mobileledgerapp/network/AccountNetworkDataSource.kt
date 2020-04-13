package info.chorimeb.mobileledgerapp.network

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest

interface AccountNetworkDataSource {
    val account: LiveData<Account>
    val accounts: LiveData<ArrayList<Account>>
    val accountEdited: LiveData<Boolean>
    val accountAdded: LiveData<Boolean>

    suspend fun getAccount(token: String, id: Int)

    suspend fun getAccounts(token: String)

    suspend fun editAccount(token: String, id: Int, editAccountRequest: EditAccountRequest)

    suspend fun addAccount(token: String, newAccountRequest: NewAccountRequest)
}