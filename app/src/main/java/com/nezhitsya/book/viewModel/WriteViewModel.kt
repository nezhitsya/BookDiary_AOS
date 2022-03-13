package com.nezhitsya.book.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class WriteViewModel: ViewModel() {

    lateinit var firebaseUser: FirebaseUser
    lateinit var ref: DatabaseReference
    lateinit var diaryId: String

    fun writeDiary(title: String, desc: String) {
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Diary")
        diaryId = ref.push().key.toString()

        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["publisher"] = firebaseUser.uid
        hashMap["diaryid"] = diaryId
        hashMap["title"] = title
        hashMap["desc"] = desc

        ref.child(diaryId).updateChildren(hashMap)
    }
}