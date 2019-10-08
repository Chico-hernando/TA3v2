package com.example.ta3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main2activity.*


class main2activity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    lateinit var ref: DatabaseReference
    lateinit var refg : DatabaseReference
    private lateinit var Database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2activity)

        ref = FirebaseDatabase.getInstance().getReference("USER SISWA")
        refg = FirebaseDatabase.getInstance().getReference("USER GURU")

        auth = FirebaseAuth.getInstance()

        daftarg.setOnClickListener {
            validasiinputguru()
        }

        daftarm.setOnClickListener {
            validasiinputsiswa()
        }


        val masuk=findViewById<TextView>(R.id.masuk)
        masuk.setOnClickListener {
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
    }

    fun validasiinputguru(){
        val nama = name.text.toString()
        val email = mail.text.toString()
        val password = pass.text.toString()
        val sekolah = sch.text.toString()
        val userj = "guru"


        when{
            nama.isEmpty()->name.error="Nama tidak boleh kosong"
            email.isEmpty()->mail.error="Email tidak boleh kosong"
            password.isEmpty()->pass.error="Password tidak boleh kosong"
            sekolah.isEmpty()->sch.error="Nama sekolah tidak boleh kosong"
            password.length < 7->pass.error="Password harus lebih dari 7"

        else->{
//            createAccount(email, password)
//            val userId = ref.push().key.toString()
//            val user = Usersg(userId, nama, email, sekolah, password, userj)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful){

                        val nama = name.text.toString()
                        val email = mail.text.toString()
                        val password = pass.text.toString()
                        val sekolah = sch.text.toString()
                        val userj = "guru"

                        val useri = auth.currentUser
                        val uid = useri!!.uid

//                      val userId = FirebaseAuth.getInstance().currentUser!!.uid
                        val user = Users(uid, nama, email, sekolah, password, userj)

                        refg.child(uid).setValue(user).addOnCompleteListener {

                            name.setText("")
                            mail.setText("")
                            sch.setText("")
                            pass.setText("")
                        }

                        Log.d(TAG, "createUser:success")
                        Toast.makeText(this,"Registrasi Berhasil",Toast.LENGTH_SHORT).show()
                        val i = Intent(this, kode_guru::class.java)
                        startActivity(i)
                        finish()

                    } else {
                        Log.w(TAG, "createUser:failure", task.exception)
                        Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()

                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }

//            refg.child(userId).setValue(user).addOnCompleteListener {
//
//                name.setText("")
//                mail.setText("")
//                sch.setText("")
//                pass.setText("")
//            }


        }
        }
    }

    fun validasiinputsiswa(){
        val nama = name.text.toString()
        val email = mail.text.toString()
        val password = pass.text.toString()
        val sekolah = sch.text.toString()
        val userj = "siswa"


        when{
            nama.isEmpty()->name.error="Nama tidak boleh kosong"
            email.isEmpty()->mail.error="Email tidak boleh kosong"
            password.isEmpty()->pass.error="Password tidak boleh kosong"
            sekolah.isEmpty()->sch.error="Nama sekolah tidak boleh kosong"
            password.length < 7->pass.error="Password harus lebih dari 7"

            else->{


                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){

                            val nama = name.text.toString()
                            val email = mail.text.toString()
                            val password = pass.text.toString()
                            val sekolah = sch.text.toString()
                            val userj = "siswa"

                            val useri = auth.currentUser
                            val uid = useri!!.uid

//                            val userId = FirebaseAuth.getInstance().currentUser!!.uid
                            val user = Users(uid, nama, email, sekolah, password, userj)

                            ref.child(uid).setValue(user).addOnCompleteListener {

                                name.setText("")
                                mail.setText("")
                                sch.setText("")
                                pass.setText("")
                            }

                            Log.d(TAG, "createUser:success")
                            Toast.makeText(this,"Registrasi Berhasil",Toast.LENGTH_SHORT).show()
                            val i = Intent(this, MainActivity::class.java)
                            startActivity(i)
                            finish()
                        } else {
                            Log.w(TAG, "createUser:failure", task.exception)
                            Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()

                           Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }

                createAccount(email, password)



            }
        }
    }


companion object {
    private const val TAG ="Register"
}
}
