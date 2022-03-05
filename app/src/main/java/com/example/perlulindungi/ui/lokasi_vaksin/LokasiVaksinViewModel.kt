package com.example.perlulindungi.ui.lokasi_vaksin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.perlulindungi.data.city.CityModel
import com.example.perlulindungi.data.city.CityRepo
import com.example.perlulindungi.data.faskes.FaskesModel
import com.example.perlulindungi.data.faskes.FaskesRepo
import com.example.perlulindungi.data.province.ProvinceModel
import com.example.perlulindungi.data.province.ProvinceRepo

class LokasiVaksinViewModel : ViewModel() {
    var faskes: LiveData<List<FaskesModel>>? = null
    var provinces: LiveData<List<ProvinceModel>>? = null
    var cities: LiveData<List<CityModel>>? = null

    private var faskesRepo = FaskesRepo("ACEH", "KAB. ACEH TIMUR");
    private var provinceRepo = ProvinceRepo();
    private var cityRepo = CityRepo();

    fun getFaskes(province: String?, city: String?) {
        val res = faskesRepo.getFaskes(province, city)
        println("GET FASKES VIEW MODEL")
        println(res)
        println(res.value)
        faskes = res
    }

    fun getProvince() {
        val res = provinceRepo.getProvinces()
        println("GET PROVINCES VIEW MODEL")
        println(res)
        provinces = res
    }

    fun getCities(province: ProvinceModel) {
        val res = cityRepo.getCities(province)
        println("GET CITIES VIEW MODEL")
        println(res)
        cities = res
    }
}