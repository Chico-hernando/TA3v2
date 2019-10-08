package com.example.ta3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.report.*

class report: AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        ref = FirebaseDatabase.getInstance().getReference("bug report")

        val metu=findViewById<ImageButton>(R.id.tutup)
       metu.setOnClickListener {
           val intent = Intent(this, home :: class.java)
           startActivity(intent)
           finish()
        }

        val kirim=findViewById<Button>(R.id.sendbug)
        kirim.setOnClickListener {
            val kirimb = ref.push().key.toString()
            val bug = et_bug.text.toString()
            val reported = Bug(bug)

            ref.child(kirimb).setValue(reported).addOnCompleteListener {

                et_bug.setText("")
            }

            Toast.makeText(this, "Laporan bug terkirim", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, home::class.java)
            startActivity(intent)
            finish()
        }
}
}