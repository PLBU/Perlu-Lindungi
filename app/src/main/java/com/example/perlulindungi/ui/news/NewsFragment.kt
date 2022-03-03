package com.example.perlulindungi.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private var _newsAdapter: NewsAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val newsAdapter get() = _newsAdapter!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        _newsAdapter = NewsAdapter()

        val newsViewModel =
            ViewModelProvider(this)[NewsViewModel::class.java]
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = newsAdapter

        newsViewModel.getAllNews()
        newsViewModel.allNewsData?.observe(viewLifecycleOwner) {
            if (it != null) {
                newsAdapter.setNewsList(it)
                newsAdapter.notifyDataSetChanged()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}