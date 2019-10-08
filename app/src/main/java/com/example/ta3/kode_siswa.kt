package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent

class kode_siswa: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kode_murid)
        val masuk=findViewById(R.id.masuk_siswa) as TextView
        masuk.setOnClickListener {
            val intent = Intent(this, home :: class.java)
            startActivity(intent)
            finish()
        }
    }
}