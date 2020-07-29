package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("checkinsCount") val checkinsCount: Int,
    @SerializedName("usersCount") val usersCount: Int,
    @SerializedName("tipCount") val tipCount: Int
)