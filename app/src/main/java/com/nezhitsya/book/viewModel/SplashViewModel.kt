package com.nezhitsya.book.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.nezhitsya.book.SplashActivity

class SplashViewModel: ViewModel() {

    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private var reference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

    fun login() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(SplashActivity()) { task ->
            if (task.isSuccessful) {
                reference.child(firebaseAuth.currentUser!!.uid).child("uid").setValue(firebaseAuth.currentUser!!.uid)
                reference.child(firebaseAuth.currentUser!!.uid).child("nickname").setValue("닉네임")
                reference.child(firebaseAuth.currentUser!!.uid).child("profileImage").setValue("https://firebasestorage.googleapis.com/v0/b/book-a32f3.appspot.com/o/profile%2Fprofile.png?alt=media&token=ff5fb4e6-14e0-4bd3-889d-7f9e0124113b")
            }
        }
    }

}