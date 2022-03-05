package com.example.perlulindungi.ui.lokasi_vaksin

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.perlulindungi.R
import com.example.perlulindungi.databinding.FragmentLokasiVaksinDetailBinding
import java.util.*

class LokasiVaksinDetailFragment : Fragment() {
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

        name = arguments?.getString("name")!!
        code = arguments?.getString("code")!!
        type = arguments?.getString("type")!!
        address = arguments?.getString("address")!!
        phone = arguments?.getString("phone")!!
        status = arguments?.getString("status")!!
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

        return binding.root
    }
}