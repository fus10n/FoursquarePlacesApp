package com.example.foursquareplacesapp.domains.net_response

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("venue") val venue: Venue,
    @SerializedName("tips") val tips: Array<Tip>,
    @SerializedName("referralId") val referralId: String
)