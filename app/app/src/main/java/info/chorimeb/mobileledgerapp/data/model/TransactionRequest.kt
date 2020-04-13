package info.chorimeb.mobileledgerapp.data.model

class NewTransactionReq(
    val accountid: Int,
    val amount: Double,
    val paiddate: String,
    val payee: String,
    val description: String,
    val category: String
) {

}
