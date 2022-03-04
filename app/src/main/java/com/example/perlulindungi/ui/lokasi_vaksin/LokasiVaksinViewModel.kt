package com.example.perlulindungi.ui.lokasi_vaksin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perlulindungi.data.faskes.FaskesModel
import com.example.perlulindungi.data.faskes.FaskesRepo

class LokasiVaksinViewModel : ViewModel() {
    var faskes: LiveData<List<FaskesModel>>? = null
    private var faskesRepo = FaskesRepo("JAWA BARAT", "KOTA BANDUNG");

    fun getFaskes() {
        val res = faskesRepo.getFaskes()
        println("GET FASKES VIEW MODEL")
        println(res)
        println(res.value)
        faskes = res
    }
}