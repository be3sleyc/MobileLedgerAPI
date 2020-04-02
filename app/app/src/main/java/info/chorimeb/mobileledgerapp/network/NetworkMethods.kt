package info.chorimeb.mobileledgerapp.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NetworkMethods {

    private val client = OkHttpClient()


    fun getRequest(url: String, body: String) : Request{
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }

    fun putRequest(url: String, body: String) : Request {
        return Request.Builder()
            .url(url)
            .put(body.toRequestBody(MEDIA_TYPE_JSON))
            .build()
    }

    fun postRequest(url: String, body: String): Request {
        return Request.Builder()
            .url(url)
            .post(body.toRequestBody(MEDIA_TYPE_JSON))
            .build()
    }

    fun sendRequest(req: Request) {
        client.newCall(req).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
//                TODO "can't connect due to self signed certificate"
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected $response")

                    for((name, value) in response.headers) {
                        println("$name:$value")
                    }
                    println(response.body!!.string())
                }
            }
        })
    }

    companion object {
        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }
}