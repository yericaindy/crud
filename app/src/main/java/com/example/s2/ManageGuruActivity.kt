package com.example.s2

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_guru.*
import kotlinx.android.synthetic.main.activity_manage_guru.btnCreate
import kotlinx.android.synthetic.main.activity_manage_guru.btnDelete
import kotlinx.android.synthetic.main.activity_manage_guru.btnUpdate
import kotlinx.android.synthetic.main.activity_manage_guru.rgGender
import kotlinx.android.synthetic.main.activity_manage_guru.txAddress
import kotlinx.android.synthetic.main.activity_manage_guru.txName
import kotlinx.android.synthetic.main.activity_manage_student.*
import org.json.JSONObject

class ManageGuruActivity : AppCompatActivity() {

    lateinit var i: Intent
    private var jenis_kelamin = "Pria"

    lateinit var spinner: Spinner
    var mapel: String? = ""

    private var hobi: String? = ""
    lateinit var cbMenonton: CheckBox
    lateinit var cbRebahan: CheckBox

    var daftar_mapel = arrayOf("Bahasa Indonesia","Matematika","Bahasa Inggris")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_guru
        )

        i = intent

        if(i.hasExtra("editmode")){

            if(i.getStringExtra("editmode").equals("1")){

                onEditMode()

            }

        }

        spinner = findViewById(R.id.listMapel) as Spinner

        spinner.adapter = ArrayAdapter<CharSequence>(this,
            android.R.layout.simple_list_item_1, daftar_mapel)

        spinner.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onNothingSelected(parent: AdapterView<*>?){
            }
            override fun onItemSelected(parent: AdapterView<*>, View: View, position: Int,id: Long?){
                mapel = parent.getItemAtPosition(position).toString()
            }
        }

        cbMenonton = findViewById(R.id.cbMenonton) as CheckBox
        cbRebahan = findViewById(R.id.cbRebahan) as CheckBox

        cbMenonton.setOnClickListener {
            if (cbMenonton.isChecked()) {
                hobi += "Menonton Film"
            }
        }
        cbMenonton.setOnClickListener {
            if (cbRebahan.isChecked()) {
                hobi += "Rebahan"
            }
        }

        rgGender.setOnCheckedChangeListener{ radioGroup, i ->
            when(i){

                R.id.radioBoy ->{
                    jenis_kelamin = "Pria"
                }
                R.id.radioGirl ->{
                    jenis_kelamin = "Wanita"
                }
            }
        }

        btnCreate.setOnClickListener {
            create()
        }
        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }
    }

    private fun onEditMode(){

        txNip.setText(i.getStringExtra("NIP"))
        txName.setText(i.getStringExtra("Nama"))
        txAddress.setText(i.getStringExtra("Alamat"))
        txNotelp.setText(i.getStringExtra("Notelp"))

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
        gender = i.getStringExtra("gender")

        if(gender.equals("Pria")){
            rgGender.check(R.id.radioBoy)
        }else{
            rgGender.check(R.id.radioGirl)
        }
    }

    private fun create(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint4.CREATE)
            .addBodyParameter("nip",txNip.text.toString())
            .addBodyParameter("nama",txName.text.toString())
            .addBodyParameter("alamat",txAddress.text.toString())
            .addBodyParameter("notelp",txNotelp.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageGuruActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
    private fun update(){

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint4.UPDATE)
            .addBodyParameter("nip",txNip.text.toString())
            .addBodyParameter("nama",txName.text.toString())
            .addBodyParameter("alamat",txAddress.text.toString())
            .addBodyParameter("notelp",txNotelp.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageGuruActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint4.DELETE+"?id="+txNip.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageGuruActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
}

