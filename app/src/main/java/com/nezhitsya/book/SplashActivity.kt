package com.nezhitsya.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener

    override fun onStart() {
        super.onStart()

//        delayScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseAuth = Firebase.auth

        init()
    }

    private fun delayScreen() {
        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe({
            firebaseAuth.addAuthStateListener(listener)
            main()
        })
    }

    private fun init() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                main()
            }
        }
    }

    private fun main() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}