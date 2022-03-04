package com.example.perlulindungi.ui.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.perlulindungi.R
import com.example.perlulindungi.databinding.FragmentNewsWebViewBinding

class NewsWebViewFragment : Fragment() {
    private var url: String = ""
    private var _binding: FragmentNewsWebViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = arguments?.getString("url")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsWebViewBinding.inflate(inflater, container, false)

        val webView = binding.webView
        val urlText = binding.newsUrlText

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView.loadUrl(this.url)
        urlText.text = this.url

        return binding.root
    }
}