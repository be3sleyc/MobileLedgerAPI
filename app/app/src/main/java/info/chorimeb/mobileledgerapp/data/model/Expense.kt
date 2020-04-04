package info.chorimeb.mobileledgerapp.data.model

import java.io.Serializable
import java.time.LocalDateTime

data class Expense(
    val id: Int,
    var accountName: String,
    val payeeId: Int,
    var paidDate: LocalDateTime,
    var payee: String,
    var amount: Double,
    var description: String,
    var category: String
) : Serializable