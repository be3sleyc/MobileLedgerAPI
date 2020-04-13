package info.chorimeb.mobileledgerapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import info.chorimeb.mobileledgerapp.data.model.Account

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(account: Account)

    @Query("select * from Accounts where id = :id")
    fun getAccount(id: Int): LiveData<Account>

    @Query("select * from Accounts")
    fun getAccounts(): LiveData<List<Account>>

    @Query("select type from Accounts")
    fun getTypes(): LiveData<List<String>>

    @Query("delete from Accounts")
    fun deleteAccounts()
}