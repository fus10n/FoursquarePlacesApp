package com.example.foursquareplacesapp.ui.main.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.foursquareplacesapp.domains.PlaceItem

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private val itemsList = ArrayList<PlaceItem>()

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        if (!::layoutInflater.isInitialized)
            layoutInflater = LayoutInflater.from(parent.context)

        return MainRecyclerViewHolder(layoutInflater, parent)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) = holder.bind(itemsList[position])

    fun setItems(items: Collection<PlaceItem>?) {
        itemsList.apply {
            if (isNotEmpty())
                clear()

            if (!items.isNullOrEmpty())
                addAll(items)
        }

        notifyDataSetChanged()
    }

}