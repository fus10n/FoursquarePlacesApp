package com.example.foursquareplacesapp.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.foursquareplacesapp.ui.main.list_adapter.MainRecyclerAdapter
import com.example.foursquareplacesapp.ui.main.list_adapter.MainRecyclerDividerDecoration
import com.example.foursquareplacesapp.R
import com.example.foursquareplacesapp.events.ContentReadyEvent
import com.example.foursquareplacesapp.network.RequestManager

import kotlinx.android.synthetic.main.activity_main.*

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : Activity() {

    private lateinit var recyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerAdapter = MainRecyclerAdapter()

        main_recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        main_recyclerview.addItemDecoration(MainRecyclerDividerDecoration(resources))
        main_recyclerview.adapter = recyclerAdapter
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onContentReadyEvent(event: ContentReadyEvent) {
        recyclerAdapter.setItems(event.items)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSearchButtonClick(view: View) {
        RequestManager.requestPlaces(main_search_edittext.text?.toString())
    }

}