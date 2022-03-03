package com.example.perlulindungi.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perlulindungi.data.news.NewsModel
import com.example.perlulindungi.data.news.NewsRepo

class NewsViewModel : ViewModel() {
    var allNewsData: LiveData<List<NewsModel>>? = null
    private var newsRepo: NewsRepo = NewsRepo()

    fun getAllNews() {
        if (allNewsData == null) {
            allNewsData = newsRepo.requestAllNews()
        }
    }
}