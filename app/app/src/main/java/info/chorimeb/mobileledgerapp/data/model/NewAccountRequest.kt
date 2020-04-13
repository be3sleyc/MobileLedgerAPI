package info.chorimeb.mobileledgerapp.data.model

data class NewAccountRequest(
    val name: String,
    val type: String,
    val balance: Double,
    val notes: String
) {

}
