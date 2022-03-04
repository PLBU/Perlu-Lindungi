package com.example.perlulindungi.data.province

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ProvinceRepo () {
    fun getProvinces(): MutableLiveData<List<ProvinceModel>> {
        var result: MutableLiveData<List<ProvinceModel>> = MutableLiveData()

        var provinceAPI: ProvinceApi = RetrofitClient().getRetrofitClient()!!.create()
        var call: Call<ProvinceResponseModel> = provinceAPI.getProvinces()

        call.enqueue(object: Callback<ProvinceResponseModel> {
            override fun onResponse(call : Call<ProvinceResponseModel>, response: Response<ProvinceResponseModel>){
                Log.d("Province", "response=$response")
                Log.d("DATA", Gson().toJson(response.body()))

                result.value = response.body()?.getData()?.toList()
            }

            override fun onFailure(call : Call<ProvinceResponseModel>, t: Throwable) {
                Log.e("FAILURE FASKES", "Failure $call")
            }
        })

        return result
    }
}