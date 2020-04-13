package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.AccountListDataSource
import info.chorimeb.mobileledgerapp.data.Result
import info.chorimeb.mobileledgerapp.data.db.AccountDao
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest

interface AccountListRepository {

    suspend fun getAccount(token: String, accountId: Int): LiveData<Account>

    suspend fun getAccounts(token: String): LiveData<List<Account>>

    suspend fun editAccount(token: String, editAccountRequest: EditAccountRequest): LiveData<Boolean>

    suspend fun addAccount(token: String, newAccountRequest: NewAccountRequest): LiveData<Boolean>
}