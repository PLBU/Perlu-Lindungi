package com.example.perlulindungi.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perlulindungi.data.faskes.FaskesModel
import com.example.perlulindungi.data.faskes.bookmark.Bookmark
import com.example.perlulindungi.data.faskes.bookmark.BookmarkRepo

class BookmarkViewModel(private val repo: BookmarkRepo) : ViewModel() {
    var bookmarkList: LiveData<List<FaskesModel>> = MutableLiveData()

    fun getBookmarkFaskes() {
        bookmarkList = repo.getAllBookmark()
    }
}