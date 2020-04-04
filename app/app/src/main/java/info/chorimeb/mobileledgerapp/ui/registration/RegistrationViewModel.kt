package info.chorimeb.mobileledgerapp.ui.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.chorimeb.mobileledgerapp.data.RegistrationRepository
import info.chorimeb.mobileledgerapp.data.Result

import info.chorimeb.mobileledgerapp.R

class RegistrationViewModel(private val registrationRepository: RegistrationRepository) :
    ViewModel() {

    private val _registrationForm = MutableLiveData<RegistrationFormState>()
    val registrationFormState: LiveData<RegistrationFormState> = _registrationForm

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    fun register(givenname: String, surname: String, email: String, password: String) {
        val result = registrationRepository.register(givenname, surname, email, password)

        if (result is Result.Success) {
            _registrationResult.value = RegistrationResult(success = RegistrationView(result.data))
        } else {
            _registrationResult.value = RegistrationResult(error = R.string.registration_failed)
        }
    }

    fun registrationDataChanged(
        givenname: String,
        surname: String,
        email: String,
        password: String
    ) {
        if (!isFirstNameValid(givenname)) {
            _registrationForm.value =
                RegistrationFormState(givennameError = R.string.invalid_firstname)
        } else if (!isLastNameValid(surname)) {
            _registrationForm.value =
                RegistrationFormState(surnameError = R.string.invalid_lastname)
        } else if (!isEmailValid(email)) {
            _registrationForm.value = RegistrationFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _registrationForm.value =
                RegistrationFormState(passwordError = R.string.invalid_password)
        } else {
            _registrationForm.value = RegistrationFormState(isDataValid = true)
        }
    }

    private fun isFirstNameValid(givenname: String): Boolean {
        return if (givenname.isNotBlank()) givenname.length < 30 else false
    }

    private fun isLastNameValid(surname: String): Boolean {
        return if (surname.isNotBlank()) surname.length < 30 else false
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }
}