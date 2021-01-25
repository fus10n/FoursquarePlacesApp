package com.example.foursquareplacesapp.ui.main

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.foursquareplacesapp.events.ContentReadyEvent
import com.example.foursquareplacesapp.network.RequestManager
import com.example.foursquareplacesapp.ui.main.list_adapter.MainRecyclerAdapter
import com.example.foursquareplacesapp.ui.main.list_adapter.MainRecyclerDividerDecoration

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivityPresenter(val activityView: MainActivityView) {

    private val recyclerAdapter = MainRecyclerAdapter()

    fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(MainRecyclerDividerDecoration(recyclerView.resources))
        recyclerView.adapter = recyclerAdapter
    }

    fun setupTextListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                RequestManager.requestPlaces(s?.toString())
            }
        })
    }

    fun start() {
        EventBus.getDefault().register(this)
    }

    fun stop() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onContentReadyEvent(event: ContentReadyEvent) {
        recyclerAdapter.setItems(event.items)
    }

}