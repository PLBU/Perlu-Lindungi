package com.example.perlulindungi.ui.lokasi_vaksin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.databinding.FragmentLokasiVaksinBinding

class LokasiVaksinFragment : Fragment() {

    private var _binding: FragmentLokasiVaksinBinding? = null
    private var _lokasiVaksinAdapter: LokasiVaksinAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val lokasiVaksinAdapter get() = _lokasiVaksinAdapter!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLokasiVaksinBinding.inflate(inflater, container, false)
        _lokasiVaksinAdapter = LokasiVaksinAdapter()

        val lokasiVaksinViewModel =
            ViewModelProvider(this)[LokasiVaksinViewModel::class.java]
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.rvLokasiVaksin

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = lokasiVaksinAdapter

        lokasiVaksinViewModel.getFaskes()
        lokasiVaksinViewModel.faskes?.observe(viewLifecycleOwner) {
            print("FRAGMENT - IT")
            println(it);
            if (it != null) {
                lokasiVaksinAdapter.setLokasiVaksinList(it.toList())
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