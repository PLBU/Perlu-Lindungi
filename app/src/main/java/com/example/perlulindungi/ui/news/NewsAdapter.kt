package com.example.perlulindungi.ui.news

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perlulindungi.R
import com.example.perlulindungi.data.news.NewsModel
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList: List<NewsModel>? = null

    class NewsViewHolder(newsRow: View): RecyclerView.ViewHolder(newsRow) {
        val newsRow: LinearLayout = newsRow.findViewById(R.id.news_row)
        val newsTitle: TextView = newsRow.findViewById(R.id.news_title)
        val newsDate: TextView = newsRow.findViewById(R.id.news_date)
        val newsPic: ImageView = newsRow.findViewById(R.id.news_img)
    }

    fun setNewsList(data: List<NewsModel>) {
        newsList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.comp_news_row, parent, false)

        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsData: NewsModel = newsList!![position]
        val date: Calendar = Calendar.getInstance()
        val dateParser = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
        val datePrettier = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
//        val intent = Intent(holder.itemView.context, NewsWebViewActivity::class.java)

        date.time = dateParser.parse(newsData.getDate())

        holder.newsTitle.text = newsData.getTitle()
        holder.newsDate.text = datePrettier.format(date.time)
        Glide.with(holder.itemView)
            .load(newsData.getPicture())
            .into(holder.newsPic)
        holder.newsRow.setOnClickListener {
            val bundle = bundleOf("url" to newsData.getUrl())
            holder.itemView.findNavController().navigate(R.id.navigation_news_web_view, bundle)
        }
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }
}

