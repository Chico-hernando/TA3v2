package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2activity.*
import kotlinx.android.synthetic.main.head_drawer.*
import kotlinx.android.synthetic.main.profiles.*

class profiles: AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profiles)

        getUserProfile()

        val keluar=findViewById<ImageButton>(R.id.kembali)
        keluar.setOnClickListener {
            val intent = Intent(this, home :: class.java)
            startActivity(intent)
            finish()
        }

        val editprof=findViewById<TextView>(R.id.edit)
        editprof.setOnClickListener {
            val intent = Intent(this, edit_profiles::class.java)
            startActivity(intent)
        }
    }

    private fun getUserProfile() {
        // [START get_user_profile]
        val user = FirebaseAuth.getInstance()
        val getUserID = user.currentUser!!.uid

        val getDatabase = FirebaseDatabase.getInstance().getReference("USER SISWA")
        val getReference = getDatabase.child(getUserID)

        getReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                val juser = p0.getValue(Users::class.java)

                val txnama = findViewById<TextView>(R.id.nama)
                val txemail = findViewById<TextView>(R.id.email)
                val txsekolah = findViewById<TextView>(R.id.school)

                txnama.text = juser?.nama
                txemail.text = juser?.email
                txsekolah.text = juser?.sekolah


//                juser?.let {
//
//                    val txnama = findViewById<TextView>(R.id.nama)
//                    val txemail = findViewById<TextView>(R.id.email)
//                    val txsekolah = findViewById<TextView>(R.id.school)
//
//                    txnama.text = it.nama
//                    txemail.text = it.email
//                    txsekolah.text = it.sekolah
//                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@profiles,
                    "menapilkan profil gagal", Toast.LENGTH_SHORT).show()
            }


        })
    }
}