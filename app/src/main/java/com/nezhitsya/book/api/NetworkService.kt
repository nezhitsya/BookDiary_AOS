package com.nezhitsya.book.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val NAVER_API_URL = "https://openapi.naver.com/"
    val clientID = "F7osXKqVTEE6Bq_djoBM"
    val clientSecret = "zvhQ_5Jt0C"

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", clientID)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(NAVER_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val naverAPI: NaverAPI = retrofit.create(NaverAPI::class.java)
}