package com.example.foursquareplacesapp.network

import android.util.Log

import com.example.foursquareplacesapp.domains.KVPair

import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.URLConnection
import java.net.URLEncoder

object NetworkUtils {

    private const val CHARSET = "UTF-8"

    /**
     * Encode the specified String into a URL compatible format.
     */
    private fun encode(requestParam: String): String =
        URLEncoder.encode(requestParam, CHARSET)

    /**
     * Write the specified key-value pair objects as request parameters into the specified URL connection.
     */
    fun writeRequestParams(connection: URLConnection, vararg paramPairs: KVPair<String, String>) {
        if (paramPairs.isNullOrEmpty())
            return

        val paramsBuilder = StringBuilder()
        for (pair in paramPairs) {
            if (paramsBuilder.isNotEmpty())
                paramsBuilder.append('&')

            paramsBuilder.append(encode(pair.key)).append('=').append(encode(pair.value))
        }

        Log.d("writing request params", paramsBuilder.toString())

        try {
            connection.doOutput = true
        } catch (ignore: IllegalStateException) {}

        var outputStream: OutputStream? = null

        try {
            outputStream = connection.getOutputStream()

            BufferedWriter(OutputStreamWriter(outputStream, CHARSET)).apply {
                write(paramsBuilder.toString())
                flush()
                close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
        }
    }

}