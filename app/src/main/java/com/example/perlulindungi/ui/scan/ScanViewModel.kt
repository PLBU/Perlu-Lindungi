package com.example.perlulindungi.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perlulindungi.data.qrcode.CheckInRepo
import com.example.perlulindungi.data.qrcode.CheckInResponseModel
import com.example.perlulindungi.data.qrcode.QrCodeModel
import kotlinx.coroutines.launch

class ScanViewModel(private val repo: CheckInRepo): ViewModel() {
    var checkInResult: MutableLiveData<CheckInResponseModel> = MutableLiveData<CheckInResponseModel>()

    fun checkIn() {
        checkInResult = repo.postQrCodeData()
    }
}