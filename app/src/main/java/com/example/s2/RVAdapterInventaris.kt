package com.example.s2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.inventaris_list.view.*

class RVAdapterInventaris(private val context: Context, private val arrayList: ArrayList<Inventaris>)
    : RecyclerView.Adapter<RVAdapterInventaris.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterInventaris.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.inventaris_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: RVAdapterInventaris.Holder, position: Int) {
        holder.view.lbIdList.text = arrayList?.get(position)?.id
        holder.view.lbNamaList.text = "Nama : "+arrayList?.get(position)?.nama
        holder.view.lbJenisList.text = "Jenis : "+arrayList?.get(position)?.jenis
        holder.view.lbJumlahList.text = "Jumlah : "+arrayList?.get(position)?.jumlah
        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ManageInventarisActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode","1")
            i.putExtra("ID",arrayList?.get(position)?.id)
            i.putExtra("Nama",arrayList?.get(position)?.nama)
            i.putExtra("Jenis",arrayList?.get(position)?.jenis)
            i.putExtra("Jumlah",arrayList?.get(position)?.jumlah)
            context.startActivity(i)
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)


}