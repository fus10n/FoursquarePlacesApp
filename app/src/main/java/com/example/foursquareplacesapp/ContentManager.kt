package com.example.foursquareplacesapp

import com.example.foursquareplacesapp.domains.PlaceItem
import com.example.foursquareplacesapp.domains.net_response.Icon
import com.example.foursquareplacesapp.domains.net_response.ResponseJson
import com.example.foursquareplacesapp.events.ContentReadyEvent

import org.greenrobot.eventbus.EventBus

object ContentManager {

    fun parseApiResponse(responseJson: ResponseJson) {
        val itemsList = ArrayList<PlaceItem>()

        // Iterate all venues from the response and make PlaceItem objects out of them.
        var icon: Icon
        for (group in responseJson.response.groups)
            group.items?.forEach { item ->
                icon = item.venue.categories[0].icon
                itemsList.add(
                    PlaceItem(
                        item.referralId,
                        icon.prefix.replace("*", icon.suffix),
                        item.venue.name,
                        item.venue.location.address
                    )
                )
            }

        EventBus.getDefault().post(ContentReadyEvent(itemsList))
    }

}