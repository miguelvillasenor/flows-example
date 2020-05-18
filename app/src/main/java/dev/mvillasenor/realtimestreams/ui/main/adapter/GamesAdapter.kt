package dev.mvillasenor.realtimestreams.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mvillasenor.realtimestreams.data.Game
import dev.mvillasenor.realtimestreams.databinding.GameItemBinding
import dev.mvillasenor.realtimestreams.ext.loadImage

class GamesAdapter(
    private val gameClicked: (String) -> Unit
) : ListAdapter<Game, GamesAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(
            GameItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position), gameClicked)
    }

    class GameViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game, clickListener: (String) -> Unit) {
            binding.cover.loadImage(game.boxArtUrl.replace("{width}x{height}", "200x300"))
            binding.title.text = game.name
            binding.root.setOnClickListener {
                clickListener(game.id)
            }
        }
    }

    class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
            oldItem == newItem
    }
}
