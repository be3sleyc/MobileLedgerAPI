package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.Expense
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExpenseListDataSource {
    fun getExpenses(id: Int): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()
        list.add(
            Expense(203, "Wells Credit", "2020-03-22 03:31:01", "", -89.50, "", "Misc")
        )
        list.add(
            Expense(202, "Wells Checking", "2020-03-22 03:31:01", "", 1205.43, "", "Misc")
        )
        list.add(
            Expense(201, "Cash", "2020-03-22 03:31:01", "", 0.00, "", "Misc")
        )
        list.add(
            Expense(204, "Wells Savings", "2020-03-22 03:31:01", "", 400.01, "", "Misc")
        )
        list.add(
            Expense(205, "Chase Amazon Credit", "2020-03-22 03:31:01", "", -180.23, "", "Misc")
        )
        list.add(
            Expense(206, "UCCU Savings", "2020-03-22 03:31:01", "", 201.45, "", "Misc")
        )
        return Result.Success(list)
    }

    fun getsRange(
        userID: Int,
        startDate: String,
        endDate: String
    ): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsCategory(userID: Int, category: String): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsCategoryRange(
        userID: Int,
        category: String,
        startDate: String,
        endDate: String
    ): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsAccount(userID: Int, accountID: Int): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsAccountRange(
        userID: Int,
        accountID: Int,
        startDate: String,
        endDate: String
    ): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsPayee(userID: Int, payee: String): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }

    fun getsPayeeRange(
        userID: Int,
        payee: String,
        startDate: String,
        endDate: String
    ): Result<ArrayList<Expense>> {
        val list = ArrayList<Expense>()

        return Result.Success(list)
    }
}