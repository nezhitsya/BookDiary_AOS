package com.nezhitsya.book.model

data class Diary (
    var uid: String? = "",
    var title: String? = "",
    var desc: String? = "",
    var image: List<String>
)