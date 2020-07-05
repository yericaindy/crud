package com.example.s2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.guru_list.view.*

class RVAAdapterGuru(private val context: Context, private val arrayList: ArrayList<Guru>) : RecyclerView.Adapter<RVAAdapterGuru.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.guru_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.lbNipList.text = arrayList?.get(position)?.nip
        holder.view.lbNamaList.text = "Nama : "+arrayList?.get(position)?.nama
        holder.view.lbAlamatList.text = "Alamat : "+arrayList?.get(position)?.alamat
        holder.view.lbNotelpList.text = "No Telp : "+arrayList?.get(position)?.notelp
        holder.view.lbGenderList.text = "Jenis Kelamin : "+arrayList?.get(position)?.jenis_kelamin
        holder.view.lbMapelList.text = "Mapel : "+arrayList?.get(position)?.mapel
        holder.view.lbHobiList.text = "Hobi : "+arrayList?.get(position)?.hobi
        holder.view.cvList.setOnClickListener {
            val i = Intent(context, ManageGuruActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("nip",arrayList?.get(position)?.nip)
            i.putExtra("name",arrayList?.get(position)?.nama)
            i.putExtra("address",arrayList?.get(position)?.alamat)
            i.putExtra("notelp",arrayList?.get(position)?.notelp)
            i.putExtra("gender",arrayList?.get(position)?.jenis_kelamin)
            i.putExtra("mapel",arrayList?.get(position)?.mapel)
            i.putExtra("hobi",arrayList?.get(position)?.hobi)
            context.startActivity(i)
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}