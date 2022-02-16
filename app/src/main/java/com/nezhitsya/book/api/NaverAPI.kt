package com.nezhitsya.book.api

import com.nezhitsya.book.model.APIResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverAPI {
    @GET("v1/search/book.json?")
    fun getSearchBook(@Query("query") query: String,
                      @Query("start")start: Int? = null,
                      @Query("display") display: Int? = null): Call<APIResult>
}