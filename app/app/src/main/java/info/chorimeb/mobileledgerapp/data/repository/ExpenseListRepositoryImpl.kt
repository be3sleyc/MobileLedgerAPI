package info.chorimeb.mobileledgerapp.data.repository

import androidx.lifecycle.LiveData
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.data.model.TransactionRequest

class ExpenseListRepositoryImpl : ExpenseListRepository {
    override suspend fun getExpense(token: String, id: Int): LiveData<Expense> {
        TODO("Not yet implemented")
    }

    override suspend fun getExpenses(token: String): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsRange(
        token: String,
        startDate: String,
        endDate: String
    ): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsCategory(token: String, category: String): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsCategoryRange(
        token: String,
        category: String,
        startDate: String,
        endDate: String
    ): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsAccount(token: String, accountID: Int): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsAccountRange(
        token: String,
        accountID: Int,
        startDate: String,
        endDate: String
    ): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsPayee(token: String, payee: String): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getsPayeeRange(
        token: String,
        payee: String,
        startDate: String,
        endDate: String
    ): LiveData<List<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(token: String, category: String): LiveData<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun editExpense(
        token: String,
        transactionRequest: TransactionRequest
    ): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun addExpense(
        token: String,
        transactionRequest: TransactionRequest
    ): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}