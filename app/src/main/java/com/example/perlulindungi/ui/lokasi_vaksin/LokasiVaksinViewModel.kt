package com.example.perlulindungi.ui.lokasi_vaksin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perlulindungi.data.faskes.FaskesModel
import com.example.perlulindungi.data.faskes.FaskesRepo

class LokasiVaksinViewModel : ViewModel() {
    private val _faskes = MutableLiveData<Array<FaskesModel>>().apply {
        value = emptyArray()
    }

    private val _province = MutableLiveData<String>().apply {
        value = ""
    }

    private val _city = MutableLiveData<String>().apply {
        value = ""
    }

    val faskes: LiveData<Array<FaskesModel>> = _faskes
    val province: LiveData<String> = _province
    val city: LiveData<String> = _city

    public fun getFaskes() {
        val faskesRepo = FaskesRepo(province.value?:"", city.value?:"");
        val res = faskesRepo.getFaskes()
        _faskes.apply { value = res.value?.getData() }
    }
}