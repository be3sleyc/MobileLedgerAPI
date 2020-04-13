package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.Account
import org.json.JSONArray

class AccountListDataSource {

    fun getAccount(userID: Int, accountID: Int): Result<Account> {
        val account = Account(200,"IOU-Brad", "P2P", "", -150.00)

        return Result.Success(account)
    }

    fun getAccounts(id: Int): Result<ArrayList<Account>> {
        val list = ArrayList<Account>()
        list.add(Account(201, "Cash", "cash", "", 0.00))
        list.add(Account(202, "Wells Checking", "debit", "", 1205.43))
        list.add(Account(203, "Wells Credit", "credit", "", -89.50))
        list.add(Account(204, "Wells Savings", "savings", "", 400.01))
        list.add(Account(205, "Chase Amazon Credit", "credit", "", -180.23))
        list.add(Account(206, "UCCU Savings", "savings", "", 201.45))

        return Result.Success(list)
    }

}