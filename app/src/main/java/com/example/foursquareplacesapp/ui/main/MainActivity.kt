package com.example.foursquareplacesapp.ui.main

import android.app.Activity
import android.os.Bundle

import com.example.foursquareplacesapp.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), MainActivityView {

    private val presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setupRecycler(main_recyclerview)
        presenter.setupTextListener(main_search_edittext)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

}