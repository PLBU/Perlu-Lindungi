package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class QrCodeModel(
    @SerializedName("qrCode")
    private var qrCode: String,

    @SerializedName("latitude")
    private var latitude: Double = 0.0,

    @SerializedName("longitude")
    private var longitude: Double = 0.0
) {
    fun getQrCode() : String {
        return qrCode
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }
}