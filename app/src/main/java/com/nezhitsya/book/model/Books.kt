package com.nezhitsya.book.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Books (
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("publisher")
    @Expose
    val publisher: String,

    @SerializedName("pubdate")
    @Expose
    val pubdate: String,

    @SerializedName("description")
    @Expose
    val description: String
)