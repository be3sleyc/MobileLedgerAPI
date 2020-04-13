package info.chorimeb.mobileledgerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime

@Entity(tableName = "Transactions")
data class Expense(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var accountname: String,
    var paiddate: String,
    var payee: String,
    var amount: Double,
    var description: String,
    var category: String
) : Serializable