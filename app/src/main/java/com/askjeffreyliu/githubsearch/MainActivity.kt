package com.askjeffreyliu.githubsearch

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.askjeffreyliu.githubsearch.adapter.ItemAdapter
import com.askjeffreyliu.githubsearch.databinding.ActivityMainBinding
import com.askjeffreyliu.githubsearch.model.ResourceState
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()
        setupViewModel()
        setupUserInput()
    }

    private fun setupRecyclerView() {
        // Creates a vertical Layout Manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter
        adapter = ItemAdapter {
            Logger.d(it.fullName)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.liveData.observe(this, Observer { resource ->
            resource.let {
                when (it.state) {
                    ResourceState.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    else -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
                it.data?.let { result ->
                    adapter.updateList(result.items)
                }
                it.message?.let { msg ->
                    Snackbar.make(binding.progressBar, msg, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupUserInput() {
        binding.button.setOnClickListener {
            val searchText = binding.editText.text.toString().trim()
            viewModel.search(searchText)
        }
    }
}