package com.example.perlulindungi.data.faskes.bookmark

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.perlulindungi.data.faskes.FaskesModel

class BookmarkRepo(context: Context) {
    private val db = Room.databaseBuilder(context, BookmarkDatabase::class.java, "Bookmark").allowMainThreadQueries().build()

    fun convertFaskesToBookmark(faskesModel: FaskesModel) : Bookmark {
        return Bookmark(
            faskesModel.id as Int,
            faskesModel.getKode(),
            faskesModel.getNama(),
            faskesModel.getKota(),
            faskesModel.getProvinsi(),
            faskesModel.getAlamat(),
            faskesModel.getLat(),
            faskesModel.getLong(),
            faskesModel.getTelp(),
            faskesModel.getJenis(),
            faskesModel.getKelas(),
            faskesModel.getStatus()
        )
    }

    fun convertBookmarkToFaskes(bookmark: Bookmark) : FaskesModel {
        return FaskesModel(
            bookmark.id,
            bookmark.kode.toString(),
            bookmark.nama.toString(),
            bookmark.kota.toString(),
            bookmark.provinsi.toString(),
            bookmark.alamat.toString(),
            bookmark.latitude!!,
            bookmark.longitude!!,
            bookmark.telp.toString(),
            bookmark.jenis_faskes.toString(),
            bookmark.kelas_rs.toString(),
            bookmark.status.toString(),
        )
    }

    fun getAllBookmark() : MutableLiveData<List<FaskesModel>> {
        val bookmarkDao: BookmarkDao = db.bookmarkDao()
        val result = MutableLiveData<List<FaskesModel>>()

        result.value = bookmarkDao.getAll().map { bookmark -> convertBookmarkToFaskes(bookmark) }

        return result
    }

    fun insertBookmark(faskesModel: FaskesModel) {
        val bookmarkDao: BookmarkDao = db.bookmarkDao()

        bookmarkDao.insertBookmark(convertFaskesToBookmark(faskesModel))
    }

    fun deleteBookmark(faskesModel: FaskesModel) {
        val bookmarkDao = db.bookmarkDao()

        bookmarkDao.deleteBookmark(convertFaskesToBookmark(faskesModel))
    }

    fun isBookmark(faskesModel: FaskesModel): Boolean {
        val bookmarkDao = db.bookmarkDao()

        val bookmarkList: List<Bookmark> = bookmarkDao.getAll().filter { bookmark -> faskesModel.id == bookmark.id }

        return bookmarkList.isNotEmpty()
    }
}