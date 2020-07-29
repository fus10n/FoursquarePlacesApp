package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class ResponseJson(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: Response
)