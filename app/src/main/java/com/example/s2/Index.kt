package com.example.s2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Index : AppCompatActivity() {

    private lateinit var btnStudent: Button
    private lateinit var btnPegawai: Button
    private lateinit var btnInventaris: Button
    private lateinit var btnGuru: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        btnStudent = findViewById(R.id.btn_student)
        btnStudent.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnPegawai = findViewById(R.id.btn_pegawai)
        btnPegawai.setOnClickListener{
            startActivity(Intent(this, Main2Activity::class.java))
        }

        btnInventaris = findViewById(R.id.btn_inventaris)
        btnInventaris.setOnClickListener{
            startActivity(Intent(this, Main3Activity::class.java))
        }
        btnGuru = findViewById(R.id.btn_guru)
        btnGuru.setOnClickListener{
            startActivity(Intent(this, Main4Activity::class.java))
        }

    }
}
