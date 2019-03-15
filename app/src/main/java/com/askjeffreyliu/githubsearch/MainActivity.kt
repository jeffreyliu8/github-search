package com.askjeffreyliu.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.askjeffreyliu.githubsearch.adapter.ItemAdapter
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: ItemAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupViewModel()
        setupUserInput()
    }

    private fun setupRecyclerView() {
        // Creates a vertical Layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter
        mAdapter = ItemAdapter()
        recyclerView.adapter = mAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer<QueryResult> {
            if (it != null) {
                mAdapter.updateList(it.items)
            } else {
                mAdapter.updateList(null)
            }
        })
    }

    private fun setupUserInput() {
        button.setOnClickListener {
            val searchText = editText.text.toString().trim()
            viewModel.search(searchText)
        }
    }
}