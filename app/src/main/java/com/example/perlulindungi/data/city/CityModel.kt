package com.example.perlulindungi.data.city

class CityModel (
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