package dev.mvillasenor.realtimestreams.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dev.mvillasenor.realtimestreams.databinding.ActivityMainBinding
import dev.mvillasenor.realtimestreams.di.ViewModelFactory
import dev.mvillasenor.realtimestreams.ui.main.adapter.GamesAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val myViewModel by viewModels<MainViewModel> { viewModelFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = GamesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AndroidInjection.inject(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                RecyclerView.VERTICAL
            )
        )

        myViewModel.gamesLiveData.observe(this) { result ->
            adapter.submitList(result)
        }
    }
}
