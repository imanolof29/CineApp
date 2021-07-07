package com.example.cine.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("_id")
    @Expose
    val id:String,
    @SerializedName("title")
    @Expose
    val title:String,
    @SerializedName("overview")
    @Expose
    val overview:String,
    @SerializedName("image")
    @Expose
    val imageURL:String,
    @SerializedName("rating")
    @Expose
    val rating:Float
    )

