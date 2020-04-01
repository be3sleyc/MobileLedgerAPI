package info.chorimeb.mobileledgerapp.ui.login

import info.chorimeb.mobileledgerapp.data.model.LoggedInUser
import java.io.Serializable

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val user: LoggedInUser
) : Serializable
