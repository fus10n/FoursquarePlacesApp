package com.example.foursquareplacesapp.domains

/**
 * Data class that represents a place object on the UI.
 */
data class PlaceItem(
    val id: String,
    val iconUrl: String,
    val venueName: String,
    val address: String
) {

    override fun equals(other: Any?): Boolean {
        return other is PlaceItem && other.id == id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + iconUrl.hashCode()
        result = 31 * result + venueName.hashCode()
        result = 31 * result + address.hashCode()
        return result
    }

}