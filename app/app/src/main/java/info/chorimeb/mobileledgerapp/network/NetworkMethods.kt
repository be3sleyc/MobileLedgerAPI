package info.chorimeb.mobileledgerapp.network

import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NetworkMethods {

    private val client = OkHttpClient()

    fun getRequest(url: String, token: String?): Request {
        return if (token == null) {
            Request.Builder()
                .url(url)
                .get()
                .build()
        } else {
            Request.Builder()
                .url(url)
                .get()
                .addHeader("auth-token", token)
                .build()
        }
    }

    fun putRequest(url: String, body: String, token: String?): Request {
        return if (token == null) {
            Request.Builder()
                .url(url)
                .put(body.toRequestBody(MEDIA_TYPE_JSON))
                .build()
        } else {
            Request.Builder()
                .url(url)
                .put(body.toRequestBody(MEDIA_TYPE_JSON))
                .addHeader("auth-token", token)
                .build()
        }
    }

    fun postRequest(url: String, body: String, token: String?): Request {
        return if (token == null) {
            Request.Builder()
                .url(url)
                .post(body.toRequestBody(MEDIA_TYPE_JSON))
                .build()
        } else {
            Request.Builder()
                .url(url)
                .post(body.toRequestBody(MEDIA_TYPE_JSON))
                .addHeader("auth-token", token)
                .build()
        }
    }

    fun sendRequest(req: Request) {
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                throw IOException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected $response")
                    val gson =
                        GsonBuilder().create().fromJson(response.body?.string(), JWT::class.java)
                    println(gson)
                    println(response.toString())
                }
            }
        })
    }

    companion object {
        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }
}

class JWT(token: String)