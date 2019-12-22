package com.menggxyz.forcastmvvm.data.response


import com.google.gson.annotations.SerializedName

data class Game(
    val commentsCount: Int,
    val followersCount: Int,
    //val groups: List<Any>,
    @SerializedName("_id")
    val id: String,
    val image: String,
    val imagePoster: String,
    val isAAA: Boolean,
    @SerializedName("NFOsCount")
    val nFOsCount: Int,
   // val protections: List<String>,
    val releaseDate: String,
    val slug: String,
    val steamPrice: Int,
    val title: String,
    val updatedAt: String,
    val url: String
    //val versions: List<Any>
)