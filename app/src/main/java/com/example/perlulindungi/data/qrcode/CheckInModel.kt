package com.example.perlulindungi.data.qrcode

import com.google.gson.annotations.SerializedName

class CheckInModel {
    @SerializedName("userStatus")
    private lateinit var userStatus: String

    @SerializedName("reason")
    private lateinit var reason: String

    fun CheckInModel(userStatus: String, reason: String) {
        this.userStatus = userStatus
        this.reason = reason
    }
}