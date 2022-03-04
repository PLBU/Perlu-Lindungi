package com.example.perlulindungi.data.city

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query;

interface CityApi {
    @GET("api/get-city")
    fun getCities(
        @Query("start_id") start_id: String?
    ) : Call<CityResponseModel>
}