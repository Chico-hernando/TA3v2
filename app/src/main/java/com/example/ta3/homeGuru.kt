package com.example.ta3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class homeGuru : AppCompatActivity() {
    private var content: FrameLayout? = null
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navigation = findViewById(R.id.bn_main) as BottomNavigationView
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

        naview.setNavigationItemSelectedListener { MenuItem ->
            when (MenuItem!!.itemId) {
                R.id.profile->startActivity(Intent(this, profiles:: class.java))
                R.id.logout->startActivity(Intent(this, MainActivity:: class.java))
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
                    val fragment = FragmentKelasGuru()
                    addFragment(fragment)
                    return true
                }
                R.id.tugas -> {
                    val fragment = FragmentTaskGuru()
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

}
