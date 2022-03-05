package com.example.perlulindungi.data.faskes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class FaskesRepo(
    private var province: String = "ACEH",
    private var city: String = "KAB. ACEH TIMUR"
) {

    fun getFaskes(_province: String?, _city: String?): MutableLiveData<List<FaskesModel>>{
        province = if (_province == null) province else _province
        city = if (_city == null) city else _city

        var result: MutableLiveData<List<FaskesModel>> = MutableLiveData()

        var faskesAPI: FaskesApi = RetrofitClient().getRetrofitClient()!!.create()
        var call: Call<FaskesResponseModel> = faskesAPI.getFaskes(province, city)

        call.enqueue(object : Callback<FaskesResponseModel>{
            override fun onResponse(call: Call<FaskesResponseModel>, response: Response<FaskesResponseModel>){
                Log.d("FASKES", "response=$response")
                Log.d("DATA", Gson().toJson(response.body()))

                result.value = response.body()?.getData()?.toList()
            }

            override fun onFailure(call : Call<FaskesResponseModel>, t: Throwable) {
                Log.e("FAILURE FASKES", "Failure $call")
            }
        })

        return result
    }
}