package com.example.perlulindungi.data.news

import com.google.gson.annotations.SerializedName

data class Enclosure (val _url: String)

class NewsModel (
    @SerializedName("title")
    private var title: String,
    @SerializedName("guid")
    private var url: String,
    @SerializedName("pubDate")
    private var date: String,
    @SerializedName("enclosure")
    private var enclosure: Enclosure,
) {
    fun getTitle() : String {
        return title
    }

    fun getUrl() : String {
        return url
    }

    fun getDate() : String {
        return date
    }

    fun getPicture() : String {
        return enclosure._url
    }
}