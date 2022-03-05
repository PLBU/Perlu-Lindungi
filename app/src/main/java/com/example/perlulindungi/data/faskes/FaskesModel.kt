package com.example.perlulindungi.data.faskes

class FaskesModel (
    var id: Int,
    private var kode: String,
    private var nama: String,
    private var kota: String,
    private var provinsi: String,
    private var alamat: String,
    private var latitude: String,
    private var longitude: String,
    private var telp: String,
    private var jenis_faskes: String,
    private var kelas_rs: String,
    private var status: String
) {
    fun getNama() : String {
        return nama;
    }

    fun getJenis() : String {
        return jenis_faskes
    }

    fun getAlamat() : String {
        return alamat
    }

    fun getTelp() : String {
        return telp;
    }

    fun getKode() : String {
        return kode
    }

    fun getStatus() : String {
        return status
    }

    fun getLat() : String {
        return latitude
    }

    fun getLong() : String {
        return longitude
    }

    fun getKota() : String {
        return kota
    }

    fun getProvinsi() : String {
        return provinsi
    }

    fun getKelas() : String {
        return kelas_rs
    }
}