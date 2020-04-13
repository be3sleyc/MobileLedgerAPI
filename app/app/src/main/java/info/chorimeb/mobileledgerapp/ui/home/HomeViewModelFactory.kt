package info.chorimeb.mobileledgerapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.chorimeb.mobileledgerapp.data.AccountListDataSource
import info.chorimeb.mobileledgerapp.data.repository.AccountListRepository
import info.chorimeb.mobileledgerapp.data.ExpenseListDataSource
import info.chorimeb.mobileledgerapp.data.repository.ExpenseListRepository

class HomeViewModelFactory : ViewModelProvider.Factory {
    private val accountRepository =
        AccountListRepository(
            AccountListDataSource()
        )
    private val expenseRepository =
        ExpenseListRepository(
            ExpenseListDataSource()
        )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(accountRepository, expenseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}