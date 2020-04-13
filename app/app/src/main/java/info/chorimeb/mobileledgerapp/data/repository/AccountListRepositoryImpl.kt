package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest

class AccountListRepositoryImpl : AccountListRepository {
    override suspend fun getAccount(token: String, accountId: Int): LiveData<Account> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccounts(token: String): LiveData<List<Account>> {
        TODO("Not yet implemented")
    }

    override suspend fun editAccount(
        token: String,
        editAccountRequest: EditAccountRequest
    ): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun addAccount(
        token: String,
        newAccountRequest: NewAccountRequest
    ): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}