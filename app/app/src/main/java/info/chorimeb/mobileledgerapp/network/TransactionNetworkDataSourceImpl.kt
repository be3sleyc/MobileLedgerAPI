package info.chorimeb.mobileledgerapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.data.model.TransactionRequest
import info.chorimeb.mobileledgerapp.utilities.NoConnectivityException

class TransactionNetworkDataSourceImpl(private val apiService: ApiService) : TransactionNetworkDataSource {
    private val _transaction = MutableLiveData<Expense>()
    override val transaction: LiveData<Expense>
        get() = _transaction

    private val _transactions = MutableLiveData<ArrayList<Expense>>()
    override val transactions: LiveData<ArrayList<Expense>>
        get() = _transactions

    private val _transactionEdited = MutableLiveData<Boolean>()
    override val transactionEdited: LiveData<Boolean>
        get() = _transactionEdited

    private val _transactionAdded = MutableLiveData<Boolean>()
    override val transactionAdded: LiveData<Boolean>
        get() = _transactionAdded

    private val _categories = MutableLiveData<ArrayList<String>>()
    override val categories: LiveData<ArrayList<String>>
        get() = _categories

    override suspend fun getTransaction(token: String, id: Int) {
        try {
            val fetchedTransaction = apiService.getTransaction(token, id)
            _transaction.postValue(fetchedTransaction)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun getTransactions(token: String) {
        try {
            val fetchedTransactions = apiService.getAllTransactions(token)
            _transactions.postValue(fetchedTransactions)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun editTransaction(token: String, id: Int, transactionRequest: TransactionRequest) {
        try {
            val editedTransaction = apiService.editTransaction(token, id, transactionRequest)
            _transactionEdited.postValue(editedTransaction)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun addTransaction(token: String, transactionRequest: TransactionRequest) {
        try {
            val addedTransaction = apiService.addTransaction(token, transactionRequest)
            _transactionAdded.postValue(addedTransaction)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun getCategories(token: String) {
        try {
            val fetchedCategories = apiService.getTransactionCategories(token)
            _categories.postValue(fetchedCategories)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}