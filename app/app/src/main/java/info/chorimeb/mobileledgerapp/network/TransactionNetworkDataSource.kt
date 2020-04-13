package info.chorimeb.mobileledgerapp.network

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.*

interface TransactionNetworkDataSource {
    val transaction: LiveData<Expense>
    val transactions: LiveData<ArrayList<Expense>>
    val transactionEdited: LiveData<Boolean>
    val transactionAdded: LiveData<Boolean>
    val categories: LiveData<ArrayList<String>>

    suspend fun getTransaction(token: String, id: Int)

    suspend fun getTransactions(token: String)

    suspend fun editTransaction(token: String, id:Int, transactionRequest: TransactionRequest)

    suspend fun addTransaction(token: String, transactionRequest: TransactionRequest)

    suspend fun getCategories(token: String)

}