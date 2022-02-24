package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class CheckInResponseModel {
    @SerializedName("success")
    private lateinit var success: String

    @SerializedName("code")
    private var code: Int = 0

    @SerializedName("message")
    private lateinit var message: String

    @SerializedName("data")
    private lateinit var data: CheckInModel

    fun CheckInResponseModel(success: String, code: Int, message: String, data: CheckInModel) {
        this.success = success
        this.code = code
        this.message = message
        this.data = data
    }
}