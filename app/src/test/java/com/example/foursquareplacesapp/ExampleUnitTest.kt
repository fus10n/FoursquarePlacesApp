package com.example.foursquareplacesapp

import com.example.foursquareplacesapp.domains.PlaceItem
import com.example.foursquareplacesapp.ui.main.list_adapter.MainRecyclerAdapter

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun adapter_setItems_isCorrect() {
        val adapter = MainRecyclerAdapter()

        val newItems = ArrayList<PlaceItem>()
        newItems.add(PlaceItem("url", "venue", "address"))
        newItems.add(PlaceItem("url", "venue", "address"))
        newItems.add(PlaceItem("url", "venue", "address"))
        try {
            adapter.setItems(newItems)
            // A NPE will be thrown on the notifyDataSetChanged() call, but the items will already be added,
            // so we can safely continue with the test.
        } catch (e: NullPointerException) {}

        assertEquals(newItems.size, adapter.itemCount)

        newItems.add(PlaceItem("url", "venue", "address"))
        try {
            adapter.setItems(newItems)
            // A NPE will be thrown on the notifyDataSetChanged() call, but the items will already be added,
            // so we can safely continue with the test.
        } catch (e: NullPointerException) {}

        assertEquals(newItems.size, adapter.itemCount)
    }

}