package info.chorimeb.mobileledgerapp.data

class RegistrationRepository(val dataSource: RegistrationDataSource) {

    fun register(
        givenname: String,
        surname: String,
        email: String,
        password: String
    ): Result<Boolean> {

        return dataSource.register(givenname, surname, email, password)
    }
}