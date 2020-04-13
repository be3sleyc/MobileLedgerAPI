package info.chorimeb.mobileledgerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.repository.AccountListRepository
import info.chorimeb.mobileledgerapp.data.repository.ExpenseListRepository
import info.chorimeb.mobileledgerapp.data.Result

class HomeViewModel(private val accountRepository: AccountListRepository, private val expenseRepository: ExpenseListRepository) : ViewModel() {

    val accountList by lazyDeferred {
        accountRepository.
    }

    fun getAccounts(userID: Int) {
        val result = accountRepository.getAccounts(userID)

        _accountResult.value = AccountListResult(success = AccountListView(ArrayList(result)))
    }

    fun getExpenses(userID: Int) {
        val result = expenseRepository.getExpenses(userID)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsRange(userID: Int, startDate: String, endDate: String) {
        val result = expenseRepository.getsRange(userID, startDate, endDate)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsCategory(userID: Int, category: String) {
        val result = expenseRepository.getsCategory(userID, category)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsCategoryRange(
        userID: Int,
        category: String,
        startDate: String,
        endDate: String
    ) {
        val result = expenseRepository.getsCategoryRange(userID, category, startDate, endDate)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsAccount(userID: Int, accountID: Int) {
        val result = expenseRepository.getsAccount(userID, accountID)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsAccountRange(
        userID: Int,
        accountID: Int,
        startDate: String,
        endDate: String
    ) {
        val result = expenseRepository.getsAccountRange(userID, accountID, startDate, endDate)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsPayee(userID: Int, payee: String) {
        val result = expenseRepository.getsPayee(userID, payee)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

    fun getsPayeeRange(
        userID: Int,
        payee: String,
        startDate: String,
        endDate: String
    ) {
        val result = expenseRepository.getsPayeeRange(userID, payee, startDate, endDate)

        if (result is Result.Success) {
            _expenseResult.value = ExpenseListResult(success = ExpenseListView(result.data))
        } else {
            _expenseResult.value = ExpenseListResult(error = R.string.expenseList_failure)
        }
    }

}