package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class CheckInResponseModel(
    @SerializedName("success")
    private var success: String,

    @SerializedName("code")
    private var code: Int = 0,

    @SerializedName("message")
    private var message: String,

    @SerializedName("data")
    private var data: CheckInModel
) {
    fun getCode() : Int {
        return code
    }

    fun getMessage() : String {
        return message
    }

    fun getSuccess() : String {
        return success
    }

    fun getData() : CheckInModel {
        return data
    }
}