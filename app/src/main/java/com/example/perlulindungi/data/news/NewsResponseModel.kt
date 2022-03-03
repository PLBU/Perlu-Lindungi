package com.example.perlulindungi.data.news

import com.google.gson.annotations.SerializedName

class NewsResponseModel (
    @SerializedName("success")
    private var success: Boolean,
    @SerializedName("results")
    private var data: List<NewsModel>
) {
    fun getSuccess() : Boolean {
        return success
    }

    fun getData() : List<NewsModel> {
        return data
    }
}