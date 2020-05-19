package dev.mvillasenor.realtimestreams.ext

import android.widget.ImageView
import android.widget.SearchView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.channels.Channel

fun ImageView.loadImage(url: String) {
    Picasso.get().cancelRequest(this)
    Picasso.get().load(url)
        .into(this)
}

val SearchView.queriesChannel: Channel<String>
    get() {
        val channel = Channel<String>()
        this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    channel.offer(newText)
                }
                return true
            }
        })
        return channel
    }
