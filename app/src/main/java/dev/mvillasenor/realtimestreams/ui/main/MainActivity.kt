package dev.mvillasenor.realtimestreams.ui.main

import android.content.Intent
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
import dev.mvillasenor.realtimestreams.ui.streams.StreamsActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import dev.mvillasenor.realtimestreams.ext.queriesChannel
import kotlinx.coroutines.channels.consumeEach
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = GamesAdapter(::onOpenGame)

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
        MainScope().launch {
            searchActionView.queriesChannel.consumeEach {
                mainViewModel.applyTextFilter(it)
            }
        }
        searchActionView.setOnCloseListener {
            mainViewModel.applyTextFilter("")
            return@setOnCloseListener true
        }
    }

    private fun onOpenGame(gameId: String) {
        val intent = Intent(this, StreamsActivity::class.java)
        intent.putExtra(StreamsActivity.ARG_GAME_ID, gameId)
        startActivity(intent)
    }
}
