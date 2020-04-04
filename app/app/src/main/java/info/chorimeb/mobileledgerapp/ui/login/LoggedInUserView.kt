package info.chorimeb.mobileledgerapp.ui.login

import info.chorimeb.mobileledgerapp.data.model.User
import java.io.Serializable

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val user: User
) : Serializable
