package com.askjeffreyliu.githubsearch

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.askjeffreyliu.githubsearch.adapter.ItemAdapter
import com.askjeffreyliu.githubsearch.databinding.ActivityMainBinding
import com.askjeffreyliu.githubsearch.extension.exhaustive
import com.askjeffreyliu.githubsearch.other.Status
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


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
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.queryResultFlow.collect { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            adapter.updateList(resource.data?.items)
                        }
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.updateList(resource.data?.items)
                        }
                        else -> {
                            binding.progressBar.visibility = View.GONE
                            resource.message?.let { msg ->
                                Snackbar.make(binding.progressBar, msg, Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }.exhaustive
                }
            }
        }
    }

    private fun setupUserInput() {
        binding.button.setOnClickListener {
            val searchText = binding.editText.text.toString().trim()
            viewModel.search(searchText)
        }
    }
}