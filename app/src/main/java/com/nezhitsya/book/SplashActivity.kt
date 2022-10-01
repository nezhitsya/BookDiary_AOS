package com.nezhitsya.book

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nezhitsya.book.viewModel.SplashViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener

    private lateinit var viewModel: SplashViewModel

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        delayScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseAuth = Firebase.auth

        init()
    }

    @SuppressLint("CheckResult")
    private fun delayScreen() {
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe {
            firebaseAuth.addAuthStateListener(listener)
        }
    }

    private fun init() {
        listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                main()
            } else {
                viewModel.login()
            }
        }

    }

    private fun main() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

}