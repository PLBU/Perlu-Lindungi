package com.example.perlulindungi.data.city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CityRepo () {
    fun getCities(): MutableLiveData<CityResponseModel> {
        var result: MutableLiveData<CityResponseModel> = MutableLiveData()

        var provinceAPI: CityApi = RetrofitClient().getRetrofitClient()!!.create()
        var call: Call<CityResponseModel> = provinceAPI.getProvinces()

        call.enqueue(object: Callback<CityResponseModel> {
            override fun onResponse(call : Call<CityResponseModel>, response: Response<CityResponseModel>){
                Log.d("Province", "response=$response")
                Log.d("DATA", Gson().toJson(response.body()))

                result.value = response.body()
            }

            override fun onFailure(call : Call<CityResponseModel>, t: Throwable) {
                Log.e("FAILURE FASKES", "Failure $call")
            }
        })

        return result
    }
}