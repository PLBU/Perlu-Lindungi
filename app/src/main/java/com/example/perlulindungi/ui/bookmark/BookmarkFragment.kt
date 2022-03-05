package com.example.perlulindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.data.faskes.bookmark.BookmarkRepo
import com.example.perlulindungi.databinding.FragmentBookmarkBinding
import com.example.perlulindungi.ui.lokasi_vaksin.LokasiVaksinAdapter

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var lokasiVaksinAdapter: LokasiVaksinAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bookmarkViewModel = BookmarkViewModel(BookmarkRepo(requireContext()))

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.rvBookmark

        lokasiVaksinAdapter = LokasiVaksinAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = lokasiVaksinAdapter

        bookmarkViewModel.getBookmarkFaskes()
        bookmarkViewModel.bookmarkList.observe(viewLifecycleOwner) {
            if (it != null) {
                lokasiVaksinAdapter.setLokasiVaksinList(it)
                lokasiVaksinAdapter.notifyDataSetChanged()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}