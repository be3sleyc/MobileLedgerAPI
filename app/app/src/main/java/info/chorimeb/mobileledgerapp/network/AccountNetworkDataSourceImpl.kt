package info.chorimeb.mobileledgerapp.network

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest

class AccountNetworkDataSourceImpl : AccountNetworkDataSource {
    override val account: LiveData<Account>
        get() = TODO("Not yet implemented")
    override val accounts: LiveData<ArrayList<Account>>
        get() = TODO("Not yet implemented")
    override val accountEdited: LiveData<Boolean>
        get() = TODO("Not yet implemented")
    override val accountAdded: LiveData<Boolean>
        get() = TODO("Not yet implemented")

    override suspend fun getAccount(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAccounts(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editAccount(editAccountRequest: EditAccountRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun addAccount(newAccountRequest: NewAccountRequest) {
        TODO("Not yet implemented")
    }
}