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

    fun writeDiary(title: String, image: String, desc: String, year: Int, month: Int, day: Int, star: Int) {
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Diary")
        diaryId = ref.push().key.toString()

        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["publisher"] = firebaseUser.uid
        hashMap["diaryid"] = diaryId
        hashMap["title"] = title
        hashMap["image"] = image
        hashMap["desc"] = desc
        hashMap["year"] = year
        hashMap["month"] = month
        hashMap["day"] = day
        hashMap["star"] = star

        ref.child(firebaseUser.uid).child(diaryId).updateChildren(hashMap)
    }

}