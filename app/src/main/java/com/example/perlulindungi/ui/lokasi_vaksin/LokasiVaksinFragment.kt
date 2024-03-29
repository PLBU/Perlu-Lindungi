package com.example.perlulindungi.ui.lokasi_vaksin

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.MainActivity
import com.example.perlulindungi.data.province.ProvinceModel
import com.example.perlulindungi.databinding.FragmentLokasiVaksinBinding

class LokasiVaksinFragment : Fragment() {

    private var _binding: FragmentLokasiVaksinBinding? = null
    private var _lokasiVaksinAdapter: LokasiVaksinAdapter? = null

    private val binding get() = _binding!!
    private val lokasiVaksinAdapter get() = _lokasiVaksinAdapter!!

    @SuppressLint("NotifyDataSetChanged", "UseRequireInsteadOfGet", "ResourceType")
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

        lokasiVaksinViewModel.getFaskes(null, null)
        lokasiVaksinViewModel.faskes?.observe(viewLifecycleOwner) {
            println("FRAGMENT - IT")
            println(it);
            if (it != null) {
                lokasiVaksinAdapter.setLokasiVaksinList(it.toList())
                lokasiVaksinAdapter.notifyDataSetChanged()
            }
        }

        lokasiVaksinViewModel.getProvince()
        lokasiVaksinViewModel.provinces?.observe(viewLifecycleOwner) {
            println("PROVINCES - IT");
            println(it)
            if (it != null) {
                val adapter: ArrayAdapter<String>
                val data: List<String>

                data = ArrayList()
                for (province in it) data.add(province.getValue())

                adapter = context?.let { it1 -> ArrayAdapter<String>(it1, 17367043, data) }!!
                binding.spinnerPilihProvinsi.adapter = adapter
            }
        }

        binding.spinnerPilihProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val adapter: ArrayAdapter<String>
                val data: List<String>

                data = ArrayList()
                adapter = context?.let { it1 -> ArrayAdapter<String>(it1, 17367043, data) }!!
                binding.spinnerPilihKota.adapter = adapter
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                var province = lokasiVaksinViewModel?.provinces?.value?.get(position)
                if(province == null) {
                    province = ProvinceModel("DKI JAKARTA", "DKI JAKARTA")
                }
                lokasiVaksinViewModel.getCities(province);
                lokasiVaksinViewModel.cities?.observe(viewLifecycleOwner) {
                    println("CITIES - IT")
                    println(it)
                    if (it != null) {
                        val adapter: ArrayAdapter<String>
                        val data: List<String>

                        data = ArrayList()
                        for (city in it) data.add(city.getValue())

                        adapter =
                            context?.let { it1 -> ArrayAdapter<String>(it1, 17367043, data) }!!
                        binding.spinnerPilihKota.adapter = adapter
                    }
                }
            }
        }

        binding.btnSearch.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val province_pos = binding.spinnerPilihProvinsi.selectedItemPosition
                var province = lokasiVaksinViewModel?.provinces?.value?.get(province_pos)

                val city_pos = binding.spinnerPilihKota.selectedItemPosition;
                var city = lokasiVaksinViewModel?.cities?.value?.get(city_pos)

                val location : Location = (activity as MainActivity).getLocation()
                val userLat = location.latitude
                val userLng = location.longitude

                Log.d("USER POSITION", userLat.toString() + "," + userLng.toString());

                lokasiVaksinViewModel.getFaskes(province?.getValue(), city?.getValue())
                lokasiVaksinViewModel.faskes?.observe(viewLifecycleOwner) {
                    println("FASKES UPDATED - IT")
                    println(it);

                    val rows = it.toList().sortedWith(Comparator { first, second ->
                        val dist1 = getDistance(first.getLat(), first.getLong(),userLat, userLng)
                        val dist2 = getDistance(second.getLat(), second.getLong(), userLat, userLng)
                        if(dist1 != dist2) {
                            if (dist1 > dist2) {
                                1
                            } else {
                                -1
                            }
                        } else {
                            first.getNama().compareTo(second.getNama())
                        }
                    })

                    if (rows != null) {
                        lokasiVaksinAdapter.setLokasiVaksinList(rows.take(5))
                        lokasiVaksinAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + (Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta))))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}