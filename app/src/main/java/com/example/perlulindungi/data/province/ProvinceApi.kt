package com.example.perlulindungi.data.province

import retrofit2.Call
import retrofit2.http.GET

interface ProvinceApi {
    @GET("api/get-province")
    fun getProvinces() : Call<ProvinceResponseModel>
}