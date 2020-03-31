package info.chorimeb.mobileledgerapp.data.model

data class Account(
    val id: Int,
    var name: String,
    var type: String,
    var notes: String,
    var balance: Double
)