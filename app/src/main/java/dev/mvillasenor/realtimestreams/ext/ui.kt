package dev.mvillasenor.realtimestreams.ext

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    Picasso.get().cancelRequest(this)
    Picasso.get().load(url)
        .into(this)
}
