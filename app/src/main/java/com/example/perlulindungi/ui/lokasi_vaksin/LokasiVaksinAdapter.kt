package com.example.perlulindungi.ui.lokasi_vaksin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perlulindungi.R
import com.example.perlulindungi.data.faskes.FaskesModel

class LokasiVaksinAdapter :
    RecyclerView.Adapter<LokasiVaksinAdapter.LokasiVaksinViewHolder>() {
    private var faskesList: List<FaskesModel>? = null

    class LokasiVaksinViewHolder(faskesRow : View): RecyclerView.ViewHolder(faskesRow) {
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
    }

    override fun getItemCount(): Int {
        return faskesList?.size ?: 0
    }


}