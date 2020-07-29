package com.example.foursquareplacesapp.network

import android.util.Log

import com.example.foursquareplacesapp.domains.KVPair

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.URLConnection
import java.net.URLEncoder

object NetworkUtils {

    private const val CHARSET = "UTF-8"

    private fun encode(requestParam: String): String =
        URLEncoder.encode(requestParam, CHARSET)

    fun writeRequestParams(connection: URLConnection, vararg paramPairs: KVPair<String, String>) {
        if (paramPairs.isEmpty())
            return

        val stringBuilder = StringBuilder()
        for (pair in paramPairs) {
            if (stringBuilder.isNotEmpty())
                stringBuilder.append('&')

            stringBuilder.append(encode(pair.key)).append('=').append(encode(pair.value))
        }

        Log.d("writing request params", stringBuilder.toString())

        connection.doOutput = true
        val outputStream = connection.getOutputStream()

        val writer = BufferedWriter(OutputStreamWriter(outputStream, CHARSET))
        writer.write(stringBuilder.toString())
        writer.flush()
        writer.close()

        outputStream.close()
    }

}