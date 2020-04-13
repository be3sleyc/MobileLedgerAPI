package info.chorimeb.mobileledgerapp.network

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.data.model.TransactionRequest

class TransactionNetworkDataSourceImpl : TransactionNetworkDataSource {
    override val transaction: LiveData<Expense>
        get() = TODO("Not yet implemented")
    override val transactions: LiveData<ArrayList<Expense>>
        get() = TODO("Not yet implemented")
    override val transactionEdited: LiveData<Boolean>
        get() = TODO("Not yet implemented")
    override val transactionAdded: LiveData<Boolean>
        get() = TODO("Not yet implemented")
    override val categories: LiveData<String>
        get() = TODO("Not yet implemented")

    override suspend fun getTransaction(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactions(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editTransaction(transactionRequest: TransactionRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun addTransaction(transactionRequest: TransactionRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(token: String) {
        TODO("Not yet implemented")
    }
}