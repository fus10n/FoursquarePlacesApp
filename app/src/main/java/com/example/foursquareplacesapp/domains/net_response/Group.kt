package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("items") val items: Array<Item>?
)