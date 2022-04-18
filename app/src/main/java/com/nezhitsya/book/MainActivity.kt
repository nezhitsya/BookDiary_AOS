package com.nezhitsya.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.nezhitsya.book.fragment.HomeFragment
import com.nezhitsya.book.fragment.MyDiaryFragment
import com.nezhitsya.book.fragment.SearchFragment
import com.nezhitsya.book.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drawer_header.*
import kotlinx.android.synthetic.main.home_include_drawer.*
import kotlinx.android.synthetic.main.toolbar_item.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_include_drawer)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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

        nav_diary.setOnClickListener {
            home_drawer_layout.closeDrawer((GravityCompat.START))
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MyDiaryFragment()).addToBackStack(null).commit()
        }

        getProfile()
    }

    private fun getProfile() {
        viewModel.getProfile().observe(this, {
            nickname.text = it.nickname.toString()
            Picasso.get().load(it.profileImage).into(profile)
        })
    }

}
