package info.chorimeb.mobileledgerapp.network

import com.google.gson.Gson
import info.chorimeb.mobileledgerapp.data.model.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "http://192.168.1.7/api/"

interface ApiService {

    // User Object Calls
    @POST("users/register")
    suspend fun registerUser(@Body registerReq: RegisterRequest): Boolean

    @PUT("users/login")
    suspend fun loginUser(@Body loginReq: LoginRequest): User

    @GET("users/logout")
    suspend fun logoutUser(@Header("auth-token") token: String): Boolean

    // Account Object Calls
    @GET("accounts/")
    suspend fun getAllAccounts(@Header("auth-token") token: String): ArrayList<Account>

    @GET("accounts/{id}")
    suspend fun getAccount(
        @Header("auth-token") token: String,
        @Path("id", encoded = false) id: Int
    ): Account

    @PUT("accounts/{id}/edit")
    suspend fun editAccount(
        @Header("auth-token") token: String,
        @Path("id", encoded = false) id: Int,
        @Body editAccountReq: EditAccountRequest
    ): Boolean

    @POST("accounts/add")
    suspend fun addAccount(
        @Header("auth-token") token: String,
        @Body newAccountReq: NewAccountRequest
    ): Boolean

    // Transaction Object Calls
    @GET("transactions/")
    suspend fun getAllTransactions(@Header("auth-token") token: String): ArrayList<Expense>

    @GET("transactions/{id}")
    suspend fun getTransaction(
        @Header("auth-token") token: String,
        @Path("id", encoded = false) id: Int
    ): Expense

    @GET("transactions/range/{start}/{stop}")
    suspend fun getTransactionsRange(
        @Header("auth-token") token: String,
        @Path("start", encoded = false) start: String,
        @Path("stop", encoded = false) stop: String
    ): ArrayList<Expense>

    @GET("transactions/categories/{category}")
    suspend fun getCategoryTransactions(
        @Header("auth-token") token: String,
        @Path("category", encoded = false) category: String
    ): ArrayList<Expense>

    @GET("transactions/categories/{category}/range/{start}/{stop}")
    suspend fun getCategoryTransactionsRange(
        @Header("auth-token") token: String,
        @Path("category", encoded = false) category: String,
        @Path("start", encoded = false) start: String,
        @Path("stop", encoded = false) stop: String
    ): ArrayList<Expense>

    @GET("transactions/payees/{payee}")
    suspend fun getPayeeTransactions(
        @Header("auth-token") token: String,
        @Path("payee", encoded = false) payee: String
    ): ArrayList<Expense>

    @GET("transactions/payees/{payee}/range/{start}/{stop}")
    suspend fun getPayeeTransactionsRange(
        @Header("auth-token") token: String,
        @Path("payee", encoded = false) payee: String,
        @Path("start", encoded = false) start: String,
        @Path("stop", encoded = false) stop: String
    ): ArrayList<Expense>

    @GET("transactions/accounts/{account}")
    suspend fun getAccountTransactions(
        @Header("auth-token") token: String,
        @Path("account", encoded = false) accountId: Int
    ): ArrayList<Expense>

    @GET("transactions/accounts/{account}/range/{start}/{stop}")
    suspend fun getAccountTransactionsRange(
        @Header("auth-token") token: String,
        @Path("account", encoded = false) accountId: Int,
        @Path("start", encoded = false) start: String,
        @Path("stop", encoded = false) stop: String
    ): ArrayList<Expense>

    @GET("transactions/categorylist")
    suspend fun getTransactionCategories(@Header("auth-token") token: String): ArrayList<String>

    @PUT("transactions/{id}/edit/")
    suspend fun editTransaction(
        @Header("auth-token") token: String,
        @Path("id", encoded = false) id: Int,
        @Body editTransactionReq: TransactionRequest
    ): Boolean

    @POST("transactions/log")
    suspend fun addTransaction(
        @Header("auth-token") token: String,
        @Body newTransactionReq: TransactionRequest
    ): Boolean

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ApiService {

            val okHttpClient =
                OkHttpClient.Builder().addInterceptor(connectivityInterceptor).build()

            return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService::class.java)
        }
    }
}