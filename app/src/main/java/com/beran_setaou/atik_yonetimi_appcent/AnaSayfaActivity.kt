package com.beran_setaou.atik_yonetimi_appcent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beran_setaou.atik_yonetimi_appcent.fragments.AddFragment
import com.beran_setaou.atik_yonetimi_appcent.fragments.HomeFragment
import com.beran_setaou.atik_yonetimi_appcent.fragments.ShopFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AnaSayfaActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val addFragment = AddFragment()
    private val ShopFragment = ShopFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_sayfa)
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)



        replaceFragment(homeFragment)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home ->replaceFragment(homeFragment)
                R.id.ic_add ->replaceFragment(addFragment)
                R.id.ic_shop ->replaceFragment(ShopFragment)


            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {


        if (fragment != null) {


            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_id, fragment)
            transaction.commit()
        }

    }
}