package info.chorimeb.mobileledgerapp.ui.registration

data class RegistrationFormState(
    val givennameError: Int? = null,
    val surnameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)