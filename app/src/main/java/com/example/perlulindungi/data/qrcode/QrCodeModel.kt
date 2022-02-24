package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class QrCodeModel {
    @SerializedName("qrCode")
    private lateinit var qrCode: String

    @SerializedName("latitude")
    private var latitude: Double = 0.0

    @SerializedName("longitude")
    private var longitude: Double = 0.0

    fun QrCodeModel(qrCode: String, latitude: Double, longitude: Double) {
        this.qrCode = qrCode
        this.latitude = latitude
        this.longitude = longitude
    }
}