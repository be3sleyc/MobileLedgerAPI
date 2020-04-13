package info.chorimeb.mobileledgerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val email: String,
    val givenname: String,
    val surname: String,
    val lastaccess: String,
    val token: String
) : Serializable {
    var displayName: String = "${this.givenname} ${this.surname}"
}
