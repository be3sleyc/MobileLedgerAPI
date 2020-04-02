package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.ui.home.AccountItem
import org.json.JSONArray

class AccountListDataSource {

    companion object {

        fun createDateSet(accountJSON: JSONArray): ArrayList<AccountItem> {
            val list = ArrayList<AccountItem>()
            for (i in 0 until accountJSON.length()) {
                println(accountJSON.get(i).toString())
            }
            return list
        }
    }

}