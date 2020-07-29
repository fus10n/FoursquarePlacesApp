package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class HereNow(
    @SerializedName("count") val count: Int,
    @SerializedName("summary") val summary: String
)