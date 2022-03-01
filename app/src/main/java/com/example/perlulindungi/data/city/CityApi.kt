package com.example.perlulindungi.data.city

import retrofit2.Call
import retrofit2.http.GET

interface CityApi {
    @GET("api/get-province")
    fun getProvinces() : Call<CityResponseModel>
}