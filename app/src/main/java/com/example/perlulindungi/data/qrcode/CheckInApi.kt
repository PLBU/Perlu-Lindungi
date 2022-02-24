package com.example.perlulindungi.data.qrcode

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInApi {
    @POST("check-in")
    fun checkInQR(@Body data: QrCodeModel): Call<CheckInResponseModel>
}