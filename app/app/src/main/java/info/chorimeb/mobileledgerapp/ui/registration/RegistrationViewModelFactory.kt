package info.chorimeb.mobileledgerapp.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.chorimeb.mobileledgerapp.data.RegistrationDataSource
import info.chorimeb.mobileledgerapp.data.RegistrationRepository

class RegistrationViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(
                registrationRepository = RegistrationRepository(
                    dataSource = RegistrationDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}