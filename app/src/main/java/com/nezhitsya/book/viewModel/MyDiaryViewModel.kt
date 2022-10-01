package com.nezhitsya.book.viewModel

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.nezhitsya.book.adapter.MyDiaryAdapter
import com.nezhitsya.book.model.Diary

class MyDiaryViewModel {

    lateinit var firebaseUser: FirebaseUser
    private val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Diary")
    var diaryList = arrayListOf<Diary>()

    fun getDiary(title: String, context: Context, recyclerView: RecyclerView) {

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        reference.child(firebaseUser.uid).addValueEventListener(object: ValueEventListener {

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                diaryList.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val diary: Diary? = dataSnapshot.getValue(Diary::class.java)
                    diary?.let {
                        diaryList.add(diary)
                    }
                }
                val adapter = MyDiaryAdapter(context, diaryList)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}