package com.example.perlulindungi.ui.lokasi_vaksin

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.R
import com.example.perlulindungi.data.faskes.FaskesModel
import com.google.gson.Gson

class LokasiVaksinAdapter :
    RecyclerView.Adapter<LokasiVaksinAdapter.LokasiVaksinViewHolder>() {
    private var faskesList: List<FaskesModel>? = null

    class LokasiVaksinViewHolder(faskesRow : View): RecyclerView.ViewHolder(faskesRow) {
        val faskesRow = faskesRow.findViewById<ConstraintLayout>(R.id.faskesRow)
        val faskesName = faskesRow.findViewById<TextView>(R.id.faskesName)
        val faskesType = faskesRow.findViewById<TextView>(R.id.faskesType)
        val faskesAddress = faskesRow.findViewById<TextView>(R.id.faskesAddress)
        val faskesPhone = faskesRow.findViewById<TextView>(R.id.faskesPhone)
        val faskesCode = faskesRow.findViewById<TextView>(R.id.faskesCode)
    }

    fun setLokasiVaksinList(data: List<FaskesModel>) {
        faskesList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LokasiVaksinViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.comp_faskes_row, parent, false)

        return LokasiVaksinViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LokasiVaksinViewHolder, position: Int) {
        println("ON BIND")
        println(faskesList)
        val faskesData: FaskesModel = faskesList!![position]

        holder.faskesName.text = faskesData.getNama()
        holder.faskesType.text = faskesData.getJenis()
        holder.faskesAddress.text = faskesData.getAlamat()
        holder.faskesPhone.text = faskesData.getTelp()
        holder.faskesCode.text = faskesData.getKode()

        var bg = Color.TRANSPARENT;
        when(faskesData.getJenis()) {
            "PUSKESMAS" -> bg = Color.parseColor("#EF5DA8")
            "RUMAH SAKIT" -> bg = Color.parseColor("#5D5FEF")
            "KLINIK" -> bg = Color.parseColor("#7879F1")
            "KKP" -> bg = Color.parseColor("#41A7F6")
            "" -> bg = bg
            else -> bg = Color.RED
        }
        holder.faskesType.setBackgroundColor(bg);

        holder.faskesRow.setOnClickListener {
            var url = "https://maps.google.com/?q="
            url += faskesData.getLat()
            url += "," + faskesData.getLong()

            val bundle = Bundle()
            bundle.putString("url", url)
            bundle.putString("faskes_data", Gson().toJson(faskesData))

            holder.itemView.findNavController()
                .navigate(R.id.navigation_lokasi_vaksin_detail, bundle)
        }
    }

    override fun getItemCount(): Int {
        return faskesList?.size ?: 0
    }


}