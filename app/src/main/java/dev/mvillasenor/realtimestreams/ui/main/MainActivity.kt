package dev.mvillasenor.realtimestreams.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dev.mvillasenor.realtimestreams.R
import dev.mvillasenor.realtimestreams.databinding.ActivityMainBinding
import dev.mvillasenor.realtimestreams.ui.main.adapter.GamesAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = GamesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AndroidInjection.inject(this)

        setupRecyclerView()

        mainViewModel.gamesLiveData.observe(this) { result ->
            adapter.submitList(result)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.search_menu, menu)
            mainViewModel.applyTextFilter("")
            setUpSearchView(menu)
        }
        return true
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                RecyclerView.VERTICAL
            )
        )
    }

    private fun setUpSearchView(menu: Menu) {
        val searchActionView = menu.findItem(R.id.search).actionView as SearchView
        searchActionView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    mainViewModel.applyTextFilter(it)
                }
                return true
            }
        })
        searchActionView.setOnCloseListener {
            mainViewModel.applyTextFilter("")
            return@setOnCloseListener true
        }
    }
}
