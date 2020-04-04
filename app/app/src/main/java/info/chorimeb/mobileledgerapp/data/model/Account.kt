package info.chorimeb.mobileledgerapp.data.model

import java.io.Serializable

data class Account(
    val id: Int,
    var name: String,
    var type: String,
    var notes: String,
    var balance: Double
) : Serializable