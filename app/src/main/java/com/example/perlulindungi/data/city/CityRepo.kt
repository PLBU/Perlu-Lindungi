package com.example.perlulindungi.data.city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.example.perlulindungi.data.province.ProvinceModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CityRepo () {
    fun getCities(province: ProvinceModel): MutableLiveData<List<CityModel>> {
        var result: MutableLiveData<List<CityModel>> = MutableLiveData()

        var cityAPI: CityApi = RetrofitClient().getRetrofitClient()!!.create()
        var call: Call<CityResponseModel> = cityAPI.getCities(province.getValue())

        call.enqueue(object: Callback<CityResponseModel> {
            override fun onResponse(call : Call<CityResponseModel>, response: Response<CityResponseModel>){
                Log.d("CITY", "response=$response")
                Log.d("DATA", Gson().toJson(response.body()))

                result.value = response.body()?.getData()?.toList()
            }

            override fun onFailure(call : Call<CityResponseModel>, t: Throwable) {
                Log.e("FAILURE CITY", "Failure $call")
            }
        })

        return result
    }
}