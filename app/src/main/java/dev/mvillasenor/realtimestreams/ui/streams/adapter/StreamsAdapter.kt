package dev.mvillasenor.realtimestreams.ui.streams.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mvillasenor.realtimestreams.data.Stream
import dev.mvillasenor.realtimestreams.databinding.StreamItemBinding
import dev.mvillasenor.realtimestreams.ext.loadImage

class StreamsAdapter : ListAdapter<Stream, StreamsAdapter.StreamViewHolder>(StreamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder =
        StreamViewHolder(
            StreamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StreamViewHolder(private val binding: StreamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stream: Stream) {
            binding.cover.loadImage(stream.thumbnailUrl.replace("{width}x{height}", "300x200"))
            binding.title.text = stream.title
            binding.user.text = "@${stream.userName}"
            binding.viewCount.text = "Viewer Count: ${stream.viewerCount}"
        }
    }

    class StreamDiffCallback : DiffUtil.ItemCallback<Stream>() {
        override fun areItemsTheSame(oldItem: Stream, newItem: Stream): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Stream, newItem: Stream): Boolean =
            oldItem == newItem
    }
}
