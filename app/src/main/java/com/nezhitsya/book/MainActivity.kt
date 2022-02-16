package com.nezhitsya.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.nezhitsya.book.fragment.HomeFragment
import com.nezhitsya.book.fragment.SearchFragment
import kotlinx.android.synthetic.main.home_include_drawer.*
import kotlinx.android.synthetic.main.toolbar_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_include_drawer)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        drawer.setOnClickListener {
            home_drawer_layout.openDrawer((GravityCompat.START))
        }

        nav_calendar.setOnClickListener {
            home_drawer_layout.closeDrawer((GravityCompat.START))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).addToBackStack(null).commit()
        }

        nav_search.setOnClickListener {
            home_drawer_layout.closeDrawer((GravityCompat.START))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SearchFragment()).addToBackStack(null).commit()
        }
    }
}