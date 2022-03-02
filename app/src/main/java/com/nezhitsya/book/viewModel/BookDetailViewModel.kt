package com.nezhitsya.book.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.nezhitsya.book.adapter.BookDetailAdapter
import com.nezhitsya.book.model.Comments

class BookDetailViewModel: ViewModel() {

    val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Comments")
    var commentList = arrayListOf<Comments>()

    fun writeComment(title: String, comment: String) {
        val commentid: String = ref.child(title).push().key.toString()
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["comment"] = comment
        hashMap["time"] = System.currentTimeMillis()
        ref.child(title).child(commentid).setValue(hashMap)
    }

    fun getComment(title: String, context: Context, recyclerView: RecyclerView) {

        ref.child(title).addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                commentList.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val comment: Comments? = dataSnapshot.getValue(Comments::class.java)
                    comment?.let {
                        commentList.add(comment)
                    }
                }
                val adapter = BookDetailAdapter(context!!, commentList)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}