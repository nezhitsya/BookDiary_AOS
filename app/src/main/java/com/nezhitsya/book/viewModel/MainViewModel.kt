package com.nezhitsya.book.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.nezhitsya.book.model.Users

class MainViewModel: ViewModel() {

    private var liveUserData = MutableLiveData<Users?>()
    lateinit var firebaseUser: FirebaseUser
    lateinit var ref: DatabaseReference

    fun getProfile() {
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: Users? = snapshot.getValue(Users::class.java)
                liveUserData.postValue(user)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}