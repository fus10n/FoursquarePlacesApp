package com.example.foursquareplacesapp.ui.main.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

import com.example.foursquareplacesapp.R
import com.example.foursquareplacesapp.domains.PlaceItem

class MainRecyclerViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        layoutInflater.inflate(R.layout.item_place, parent, false)
    ) {

    fun bind(item: PlaceItem) {
        itemView.findViewById<ImageView>(R.id.place_item_icon).apply {
            Glide.with(this).load(item.iconUrl).into(this)
        }
        itemView.findViewById<TextView>(R.id.place_item_venue).text = item.venueName
        itemView.findViewById<TextView>(R.id.place_item_address).text = item.address
    }

}