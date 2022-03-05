package com.example.perlulindungi.data.faskes.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "kode") val kode: String?,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "kota") val kota: String?,
    @ColumnInfo(name = "provinsi") val provinsi: String?,
    @ColumnInfo(name = "alamat") val alamat: String?,
    @ColumnInfo(name = "latitude") val latitude: String?,
    @ColumnInfo(name = "longitude") val longitude: String?,
    @ColumnInfo(name = "telp") val telp: String?,
    @ColumnInfo(name = "jenis_faskes") val jenis_faskes: String?,
    @ColumnInfo(name = "kelas_rs") val kelas_rs: String?,
    @ColumnInfo(name = "status") val status: String?
)