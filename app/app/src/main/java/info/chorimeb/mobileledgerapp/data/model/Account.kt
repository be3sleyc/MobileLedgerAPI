package info.chorimeb.mobileledgerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var name: String,
    var type: String,
    var notes: String,
    var balance: Double
) : Serializable