package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.db.AccountDao
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.EditAccountRequest
import info.chorimeb.mobileledgerapp.data.model.NewAccountRequest
import info.chorimeb.mobileledgerapp.network.AccountNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountListRepositoryImpl(
    private val accountDao: AccountDao,
    accountNetworkDataSource: AccountNetworkDataSource
) : AccountListRepository {

    init {
        accountNetworkDataSource.apply {
            account.observeForever { fetchedAccount ->
                persistFetchedAccount(fetchedAccount)
            }

            accounts.observeForever { fetchedAccountList ->
                persistFetchedAccountList(fetchedAccountList)
            }

            accountEdited.observeForever { editSuccess ->

            }

            accountAdded.observeForever { addSuccess ->

            }
        }
    }

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

    private fun persistFetchedAccount(fetchedAccount: Account) {
        GlobalScope.launch(Dispatchers.IO) {
            accountDao.upsert(fetchedAccount)
        }
    }

    private fun persistFetchedAccountList(fetchedAccountList: List<Account>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (account in fetchedAccountList) {
                accountDao.upsert(account)
            }
        }
    }

}