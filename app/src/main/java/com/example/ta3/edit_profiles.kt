package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.icu.text.Transliterator
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.edit_profiles.*

class edit_profiles: AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profiles)

        update()

        val kembali=findViewById<ImageButton>(R.id.balik)
        kembali.setOnClickListener {
            val intent = Intent(this, profiles :: class.java)
            startActivity(intent)
            finish()
        }

    }


    fun update(){

        user = FirebaseAuth.getInstance()
        val curuser =user.currentUser
        val getUserID = user.currentUser!!.uid

        val getDatabase = FirebaseDatabase.getInstance().getReference("USER SISWA")
        val getReference = getDatabase.child(getUserID)

                getReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {

                val juser = p0.getValue(Users::class.java)

                val namaus = findViewById<EditText>(R.id.ednama)
                val sekolahus = findViewById<EditText>(R.id.edschool)

                ednama.setText(juser!!.nama)
                edschool.setText(juser.sekolah)

//                val userid = juser.id
//                val usermail = juser.email
//                val userpass = juser.password
//                val userole = juser.user

                val namaed = ednama.text.toString().trim()
                val sekolahed = edschool.text.toString().trim()

                val save=findViewById<TextView>(R.id.save)
                save.setOnClickListener {

                    when {
                        namaed.isEmpty() -> ednama.error = "Nama tidak boleh kosong"
                        sekolahed.isEmpty() -> edschool.error = "Nama sekolah tidak boleh kosong"

                        else -> {
                            val upuser = Users(getUserID, namaed, juser.email, sekolahed, juser.password, juser.user)
                            getDatabase.child(getUserID).setValue(upuser).addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(this@edit_profiles, "Profil telah di update", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(baseContext, "update profil gagal", Toast.LENGTH_SHORT).show()
                                    Toast.makeText(this@edit_profiles, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                }

                            }
                        }
                    }
                    val upuser = UserProfileChangeRequest.Builder()
                        .setDisplayName(namaed)
                        .build()

                    curuser?.updateProfile(upuser)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                Log.d(TAG,"User Profile updated")
                            }
                        }

                    val intent = Intent(this@edit_profiles, profiles::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@edit_profiles,
                    "menapilkan profil gagal", Toast.LENGTH_SHORT).show()
            }

        })

        var duser = Users()

        var namaus = findViewById<EditText>(R.id.ednama)
        var sekolahus = findViewById<EditText>(R.id.edschool)

        namaus.setText(duser.nama)
        sekolahus.setText(duser.sekolah)

        val namaed = ednama.text.toString().trim()
        val sekolahed = edschool.text.toString().trim()
    }

    companion object {
        private const val TAG ="Edit Profile"
    }
}