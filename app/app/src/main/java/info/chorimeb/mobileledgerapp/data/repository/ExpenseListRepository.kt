package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.ExpenseListDataSource
import info.chorimeb.mobileledgerapp.data.LiveData
import info.chorimeb.mobileledgerapp.data.db.TransactionDao
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.data.model.TransactionRequest

interface ExpenseListRepository {

    suspend fun getExpense(token: String, id: Int): LiveData<Expense>

    suspend fun getExpenses(token: String) : LiveData<List<Expense>>

    suspend fun getsRange(token: String, startDate: String, endDate: String): LiveData<List<Expense>>

    suspend fun getsCategory(token: String, category: String): LiveData<List<Expense>>

    suspend fun getsCategoryRange(token: String, category: String, startDate: String, endDate: String): LiveData<List<Expense>>

    suspend fun getsAccount(token: String, accountID: Int): LiveData<List<Expense>>

    suspend fun getsAccountRange(token: String, accountID: Int, startDate: String, endDate: String): LiveData<List<Expense>>

    suspend fun getsPayee(token: String, payee: String): LiveData<List<Expense>>

    suspend fun getsPayeeRange(token: String, payee: String, startDate: String, endDate: String): LiveData<List<Expense>>

    suspend fun getCategories(token: String, category: String): LiveData<List<String>>

    suspend fun editExpense(token: String, transactionRequest: TransactionRequest): LiveData<Boolean>

    suspend fun addExpense(token: String, transactionRequest: TransactionRequest): LiveData<Boolean>

}