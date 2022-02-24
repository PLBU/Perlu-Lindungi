package com.example.perlulindungi.data.qrcode

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.perlulindungi.data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class CheckInRepo {
    private lateinit var data: QrCodeModel

    fun CheckInRepo(data: QrCodeModel) {
        this.data = data
    }

    fun postQrCodeData() {
        val checkInApi : CheckInApi = RetrofitClient().getRetrofitClient()!!.create()
        val call : Call<CheckInResponseModel> = checkInApi.checkInQR(data)

        call.enqueue(object : Callback<CheckInResponseModel> {
            override fun onResponse(
                call: Call<CheckInResponseModel>,
                response: Response<CheckInResponseModel>
            ) {
                Log.d("CHECK IN QRCode", "response=$response")
            }

            override fun onFailure(
                call: Call<CheckInResponseModel>,
                t: Throwable
            ) {
                Log.e("CHECK IN QRCode", "Failure $call")
            }
        })
    }
}