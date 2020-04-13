package info.chorimeb.mobileledgerapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.data.model.User

@Database(
    entities = [User::class, Expense::class, Account::class],
    version = 1
)
abstract class LedgerDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        var instance: LedgerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LedgerDatabase::class.java,
                "ledger.db"
            )
                .build()
    }
}