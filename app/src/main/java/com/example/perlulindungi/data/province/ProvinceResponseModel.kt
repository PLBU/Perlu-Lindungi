package com.example.perlulindungi.data.province

import com.google.gson.annotations.SerializedName

class ProvinceResponseModel (
    @SerializedName("success")
     private var success: String,

    @SerializedName("code")
     private var code: Int = 0,

    @SerializedName("message")
     private var message: String,

    @SerializedName("results")
     private var data: Array<ProvinceModel>,
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

    fun getData() : Array<ProvinceModel> {
        return data
    }
}