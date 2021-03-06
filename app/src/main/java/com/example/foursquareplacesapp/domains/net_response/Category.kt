package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("pluralName") val pluralName: String,
    @SerializedName("shortName") val shortName: String,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("primary") val primary: Boolean
)