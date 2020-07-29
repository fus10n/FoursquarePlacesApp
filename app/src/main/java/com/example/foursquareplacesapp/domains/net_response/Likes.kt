package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("count") val count: Int,
    @SerializedName("groups") val groups: Array<Any?>?,
    @SerializedName("summary") val summary: String
)