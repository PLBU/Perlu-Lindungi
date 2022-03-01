package com.example.perlulindungi.data.faskes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query;

interface FaskesApi {
    @GET("api/get-faskes-vaksinasi")
    fun getFaskes(
        @Query("province") province: String,
        @Query("city") city: String
    ) : Call<FaskesResponseModel>
}