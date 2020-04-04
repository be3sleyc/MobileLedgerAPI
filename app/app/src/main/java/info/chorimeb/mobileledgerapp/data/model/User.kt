package info.chorimeb.mobileledgerapp.data.model

import java.io.Serializable
import java.time.LocalDateTime

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
        val userId: Int,
        val givenName: String,
        val surName: String,
        val email: String,
        val lastAccess: LocalDateTime,
        val token: String
) : Serializable { val displayName: String = "$givenName  $surName"}
