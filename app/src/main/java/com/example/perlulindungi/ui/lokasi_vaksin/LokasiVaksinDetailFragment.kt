package com.example.perlulindungi.ui.lokasi_vaksin

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.perlulindungi.R
import com.example.perlulindungi.data.faskes.FaskesModel
import com.example.perlulindungi.data.faskes.bookmark.BookmarkRepo
import com.example.perlulindungi.databinding.FragmentLokasiVaksinDetailBinding
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class LokasiVaksinDetailFragment : Fragment() {
    private lateinit var faskesData: FaskesModel
    private var name: String = "";
    private var code: String = "";
    private var type: String = "";
    private var address: String = "";
    private var phone: String = "";
    private var status: String = "";
    private var url: String = "";
    private var _binding: FragmentLokasiVaksinDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        faskesData = Gson().fromJson(arguments?.getString("faskes_data"), FaskesModel::class.java)
        name = faskesData.getNama()
        code = faskesData.getKode()
        type = faskesData.getJenis()
        address = faskesData.getAlamat()
        phone = faskesData.getTelp()
        status = faskesData.getStatus()
        url = arguments?.getString("url")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLokasiVaksinDetailBinding.inflate(inflater, container, false)

        val faskesTitle = binding.faskesDetailName
        val faskesCode = binding.faskesDetailCode
        val faskesType = binding.faskesDetailType
        val faskesAddress = binding.faskesDetailAddress
        val faskesPhone = binding.faskesDetailPhone
        val faskesStatus = binding.faskesDetailStatus
        val faskesImage = binding.faskesDetailImageView
        val bookmartBtn = binding.btnFaskesDetailBookmark
        val mapsBtn = binding.btnFaskesDetailMap

        faskesTitle.text = this.name
        faskesCode.text = this.code
        faskesType.text = this.type
        faskesAddress.text = this.address
        faskesPhone.text = this.phone
        faskesStatus.text = this.status
        faskesImage.setImageResource(R.drawable.ic_checkmark)
        faskesImage.setBackgroundColor(resources.getColor(R.color.green))

        var bg = Color.TRANSPARENT;
        when(this.type) {
            "PUSKESMAS" -> bg = Color.parseColor("#EF5DA8")
            "RUMAH SAKIT" -> bg = Color.parseColor("#5D5FEF")
            "KLINIK" -> bg = Color.parseColor("#7879F1")
            "KKP" -> bg = Color.parseColor("#41A7F6")
            "" -> bg = bg
            else -> bg = Color.RED
        }
       faskesType.setBackgroundColor(bg);

        mapsBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var gmapsURL : String = String.format(Locale.ENGLISH, url)
                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gmapsURL))
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        })

        bookmartBtn.setOnClickListener {
            val bookmarkRepo = BookmarkRepo(requireContext())
            var toastText = ""

            if (bookmarkRepo.isBookmark(faskesData)) {
                bookmarkRepo.deleteBookmark(faskesData)
                toastText = "Bookmark faskes dihapus!"
            } else {
                bookmarkRepo.insertBookmark(faskesData)
                toastText = "Berhasil bookmark faskes!"
            }

            Toast.makeText(requireContext(), toastText, Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}