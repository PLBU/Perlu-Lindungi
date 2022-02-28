package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class CheckInModel(
    @SerializedName("userStatus")
    private var userStatus: String,
    @SerializedName("reason")
    private var reason: String
) {
    fun getUserStatus() : String {
        return userStatus
    }

    fun getReason() : String {
        return reason
    }
}