package com.askjeffreyliu.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.askjeffreyliu.githubsearch.adapter.ItemAdapter
import com.askjeffreyliu.githubsearch.model.QueryResult
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: ItemAdapter
    private lateinit var viewModel: MainViewModel

    private val debonce_time_milli = 500L

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
        editText.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""

            override fun afterTextChanged(s: Editable) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                // Start a coroutine
                GlobalScope.launch {
                    delay(debonce_time_milli)
                    if (searchText != searchFor) {
                        return@launch
                    }

                    viewModel.search(s.toString())
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }
}
