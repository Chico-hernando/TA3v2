package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent

class kode_guru: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kode_guru)
        val masuk=findViewById(R.id.signin) as TextView
        masuk.setOnClickListener {
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }
}