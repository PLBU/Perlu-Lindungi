package com.example.perlulindungi.data.qrcode

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import com.example.perlulindungi.data.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class CheckInRepo(
    private val data: QrCodeModel
) {

    fun postQrCodeData(): MutableLiveData<CheckInResponseModel> {
        val result: MutableLiveData<CheckInResponseModel> = MutableLiveData()

        val checkInApi : CheckInApi = RetrofitClient().getRetrofitClient()!!.create()
        val call : Call<CheckInResponseModel> = checkInApi.checkInQR(data)

        Log.d("DATA", Gson().toJson(data))

        call.enqueue(object : Callback<CheckInResponseModel> {
            override fun onResponse(
                call: Call<CheckInResponseModel>,
                response: Response<CheckInResponseModel>
            ) {
                Log.d("CHECK IN QRCode", "response=$response")
                Log.d("RESPONSE DATA", Gson().toJson(response.body()))

                result.value = response.body()
            }

            override fun onFailure(
                call: Call<CheckInResponseModel>,
                t: Throwable
            ) {
                Log.e("CHECK IN QRCode", "Failure $call")
            }
        })

        return result
    }
}