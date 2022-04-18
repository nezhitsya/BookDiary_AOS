package com.nezhitsya.book.model

data class Diary (
    var diaryid: String? = "",
    var title: String? = "",
    var desc: String? = "",
    var image: String? = "",
    var year: Int? = 0,
    var month: Int? = 0,
    var day: Int? = 0
)