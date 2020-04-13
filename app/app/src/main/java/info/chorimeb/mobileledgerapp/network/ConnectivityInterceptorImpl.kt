package info.chorimeb.mobileledgerapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import info.chorimeb.mobileledgerapp.utilities.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response


class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline() && !isNetworkConnected())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities == null) {
            println("Not online")
            return false
        }
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun isNetworkConnected(): Boolean {
        val cm =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            if (nc != null) {
                return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_VPN
                ) || nc.hasTransport(
                    NetworkCapabilities.TRANSPORT_LOWPAN
                )
            }
        }
        println("network connection false")
        return false
    }
}