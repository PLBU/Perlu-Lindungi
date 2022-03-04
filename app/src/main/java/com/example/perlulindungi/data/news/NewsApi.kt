package com.example.perlulindungi.data.news

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("api/get-news")
    fun fetchAllNews(): Call<NewsResponseModel>
}