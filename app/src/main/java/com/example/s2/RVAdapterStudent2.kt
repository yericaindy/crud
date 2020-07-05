package com.example.s2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_list.view.*

class RVAAdapterStudent2(private val context: Context, private val arrayList: ArrayList<Students2>)
    : RecyclerView.Adapter<RVAAdapterStudent2.Holder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAAdapterStudent2.Holder2 {
        return Holder2(LayoutInflater.from(parent.context).inflate(R.layout.student_list2,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: RVAAdapterStudent2.Holder2, position: Int) {
        holder.view.lbNimList.text = arrayList?.get(position)?.nim
        holder.view.lbNameList.text = "Nama : "+arrayList?.get(position)?.name
        holder.view.lbAddressList.text = "Alamat : "+arrayList?.get(position)?.address
        holder.view.lbGenderList.text = "notelp : "+arrayList?.get(position)?.notelp
        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ManageStudentActivity2::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode","1")
            i.putExtra("nim",arrayList?.get(position)?.nim)
            i.putExtra("name",arrayList?.get(position)?.name)
            i.putExtra("address",arrayList?.get(position)?.address)
            i.putExtra("notelp",arrayList?.get(position)?.notelp)
            context.startActivity(i)
        }
    }

    class Holder2(val view: View) : RecyclerView.ViewHolder(view)


}