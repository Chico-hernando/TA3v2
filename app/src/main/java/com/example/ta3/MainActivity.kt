package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
            finish()
        }

        val masuk=findViewById(R.id.daftar) as TextView
        masuk.setOnClickListener {
            val intent = Intent(this, main2activity :: class.java)
            startActivity(intent)
            finish()
        }
        val mlebu=findViewById(R.id.masuk) as TextView
        mlebu.setOnClickListener {
            validasiinput()
            finish()
        }
        auth = FirebaseAuth.getInstance()
    }


    fun validasiinput(){
        val email = emailinput.text.toString()
        val password = passinput.text.toString()

        when{
            email.isEmpty()->emailinput.error="Email tidak boleh kosong"
            password.isEmpty()->passinput.error="Password tidak boleh kosong"
            password.length < 7->passinput.error="Password harus lebih dari 7"

            else->{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {task ->

                        if (task.isSuccessful){
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, home::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Login gagal", Toast.LENGTH_SHORT).show()

                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }

                    }

            }
        }
    }

}
