package com.example.foursquareplacesapp.ui.main.list_adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.example.foursquareplacesapp.R

class MainRecyclerDividerDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val verticalSpacing = resources.getDimensionPixelSize(R.dimen.main_items_divider_height)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpacing
    }

}