package com.example.perlulindungi.data

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient: Application() {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder().build()
            retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://perludilindungi.herokuapp.com/")
                .build()
        }
        return retrofit
    }
}