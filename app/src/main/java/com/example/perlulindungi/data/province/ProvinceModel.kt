package com.example.perlulindungi.data.province

class ProvinceModel (
    private var key: String,
    private var value: String
) {
    fun getKey() : String {
        return key;
    }

    fun getValue() : String {
        return value;
    }
}