package info.chorimeb.mobileledgerapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.chorimeb.mobileledgerapp.data.model.Expense

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(expense: Expense)

    @Query("select * from Transactions where id = :id")
    fun getTransaction(id: Int): LiveData<Expense>

    @Query("select * from Transactions")
    fun getTransactions(): LiveData<List<Expense>>

    @Query("select category from Transactions")
    fun getCategories(): LiveData<List<String>>

    @Query("delete from Transactions")
    fun deleteTransactions()
}