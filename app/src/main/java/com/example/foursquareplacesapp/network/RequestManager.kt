package com.example.foursquareplacesapp.network

import android.os.Handler
import android.os.Looper
import android.util.Log

import com.example.foursquareplacesapp.BuildConfig
import com.example.foursquareplacesapp.ContentManager
import com.example.foursquareplacesapp.domains.KVPair
import com.example.foursquareplacesapp.domains.net_response.ResponseJson
import com.example.foursquareplacesapp.events.ContentReadyEvent

import com.google.gson.GsonBuilder

import java.io.IOException
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.net.ssl.HttpsURLConnection

import org.greenrobot.eventbus.EventBus

object RequestManager {

    private const val API_URL = "https://api.foursquare.com/v2/venues/explore"
    private const val PARAM_CLIENT_ID = "client_id"
    private const val PARAM_CLIENT_SECRET = "client_secret"
    private const val PARAM_DATE = "v"
    private const val PARAM_LAT_LON = "ll"
    private const val PARAM_QUERY = "query"
    private const val REQUEST_DELAY_MILLIS: Long = 500

    private val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.UK)
    private val gson = GsonBuilder().create()
    private val handler = Handler(Looper.getMainLooper())

    private var waitingRunnable: Runnable? = null

    /**
     * This function basically prevents accidental double-click on the Search button.
     * Also, in case searching happens while text is being written, avoid making unnecessary requests
     * before the user has typed the whole search phrase.
     */
    fun requestPlaces(query: String?) {
        // If a request is already pending, replace it with the new one.
        if (waitingRunnable != null) {
            handler.removeCallbacks(waitingRunnable!!)
            waitingRunnable = null
        }

        if (!EventBus.getDefault().hasSubscriberForEvent(ContentReadyEvent::class.java))
            return

        if (query.isNullOrEmpty()) {
            EventBus.getDefault().post(ContentReadyEvent(null))
            return
        }

        // Send a delayed request.

        waitingRunnable = Runnable {
            Thread { sendRequest(query.trim()) }.start()
            waitingRunnable = null
        }

        handler.postDelayed(waitingRunnable!!, REQUEST_DELAY_MILLIS)
    }

    /**
     * The actual function where the HTTP request is sent.
     * Currently contains some hardcoded values for the sake of testing.
     */
    private fun sendRequest(query: String) {
        val urlConnection = URL(API_URL).openConnection() as HttpsURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.doInput = true
        urlConnection.setRequestProperty("Content-Type", "application/json")

        NetworkUtils.writeRequestParams(
            urlConnection,
            KVPair(PARAM_CLIENT_ID, BuildConfig.CLIENT_ID),
            KVPair(PARAM_CLIENT_SECRET, BuildConfig.CLIENT_SECRET),
            KVPair(PARAM_DATE, dateFormat.format(Date())),
            KVPair(PARAM_LAT_LON, "40.7243,-74.0018"),
            KVPair(PARAM_QUERY, query)
        )

        try {
            urlConnection.connect()
            val responseCode = urlConnection.responseCode

            Log.d("response code", responseCode.toString())
            Log.d("response message", urlConnection.responseMessage)

            if (responseCode in 200..399) {
                val response = gson.fromJson(InputStreamReader(urlConnection.inputStream), ResponseJson::class.java)
                ContentManager.parseApiResponse(response)
            } else {
                Log.e("error stream", InputStreamReader(urlConnection.errorStream).readText())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: RuntimeException) {
            e.printStackTrace()
        } finally {
            urlConnection.disconnect()
        }
    }

}