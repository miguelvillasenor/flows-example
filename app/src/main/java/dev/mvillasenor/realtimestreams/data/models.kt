package dev.mvillasenor.realtimestreams.data

import com.google.gson.annotations.SerializedName

data class Response<T>(
    val data: List<T>,
    val pagination: Pagination
)

data class Pagination(
    var cursor: String
)

data class Game(
    val id: String,
    val name: String,
    @SerializedName("box_art_url")
    val boxArtUrl: String
)

data class Stream(
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("game_name")
    val gameId: String,
    val type: String,
    val title: String,
    @SerializedName("viewer_count")
    val viewerCount: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String
)
