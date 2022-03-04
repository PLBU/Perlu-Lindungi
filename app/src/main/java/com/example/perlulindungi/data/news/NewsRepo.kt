package com.example.perlulindungi.data.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class NewsRepo() {
    fun requestAllNews(): MutableLiveData<List<NewsModel>> {
        val result: MutableLiveData<List<NewsModel>> = MutableLiveData()

        val newsApi : NewsApi = RetrofitClient().getRetrofitClient()!!.create()
        val call : Call<NewsResponseModel> = newsApi.fetchAllNews()

        call.enqueue(object : Callback<NewsResponseModel> {
            override fun onResponse(
                call: Call<NewsResponseModel>,
                response: Response<NewsResponseModel>
            ) {
                Log.d("News API", "response=$response")
                Log.d("News API", Gson().toJson(response.body() ))
                result.value = response.body()?.getData()
            }

            override fun onFailure(
                call: Call<NewsResponseModel>,
                t: Throwable
            ) {
                Log.e("News API", "Failure $call")
            }
        })

        return result
    }
}