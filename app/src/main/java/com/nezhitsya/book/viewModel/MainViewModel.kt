package com.nezhitsya.book.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.nezhitsya.book.model.Users

class MainViewModel: ViewModel() {

    lateinit var firebaseUser: FirebaseUser
    lateinit var ref: DatabaseReference
    lateinit var user: Users

    fun getProfile(): MutableLiveData<Users> {
        val liveUserData = MutableLiveData<Users>()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    user = it.getValue(Users::class.java)!!
                }
            }
            liveUserData.value = user
        }
        return liveUserData
    }
}