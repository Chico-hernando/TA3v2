package com.example.ta3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.head_drawer.*
import kotlinx.android.synthetic.main.profiles.*
import java.util.zip.Inflater

class home : AppCompatActivity() {
    private var content: FrameLayout? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navigation = findViewById<BottomNavigationView>(R.id.bn_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = FragmentKelas()
        addFragment(fragment)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        toggle.isDrawerIndicatorEnabled = true

        getUserProfile()

        naview.setNavigationItemSelectedListener { MenuItem ->
            when (MenuItem!!.itemId) {
                R.id.profile->startActivity(Intent(this, profiles:: class.java))
                R.id.logout->logout()
                R.id.report->startActivity(Intent(this, report:: class.java))

            }
            true
        }
        val profil =findViewById<ImageView>(R.id.btnprofil)
        profil.setOnClickListener {
            this.drawer.openDrawer(GravityCompat.START)
        }


    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.kelas -> {
            val fragment = FragmentKelas()
            addFragment(fragment)
            return true
        }
            R.id.tugas -> {
            val fragment = FragmentTask()
            addFragment(fragment)
            return true
        }
            R.id.pesan -> {
            val fragment = Fragmentmessage()
            addFragment(fragment)
            return true
        }

        }
        return false
    }
}

    private fun addFragment(fragment: Fragment){
        val transaction =supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            transaction.replace(R.id.content, fragment)
            transaction.commit()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun logout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, MainActivity:: class.java))
        finish()
    }

    private fun getUserProfile() {
        // [START get_user_profile]
        val user = FirebaseAuth.getInstance()
        var GetUserID = user.currentUser!!.uid

        var getDatabase = FirebaseDatabase.getInstance().getReference()
        var getReference = getDatabase.child("USER SISWA").child(GetUserID)

        getReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@home,
                    "menapilkan profil gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                val txnama = findViewById<TextView>(R.id.namau)
                val txsekolah = findViewById<TextView>(R.id.sekolu)
                val juser = p0.getValue(Users::class.java)
                juser?.let {

                    namau.text = it.nama
                    sekolu.text = it.sekolah
                }
            }
        })
    }


}
