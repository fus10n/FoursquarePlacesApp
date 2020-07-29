package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Tip(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String,
    @SerializedName("canonicalUrl") val canonicalUrl: String,
    @SerializedName("likes") val likes: Likes,
    @SerializedName("agreeCount") val agreeCount: Int,
    @SerializedName("user") val user: Any?
)