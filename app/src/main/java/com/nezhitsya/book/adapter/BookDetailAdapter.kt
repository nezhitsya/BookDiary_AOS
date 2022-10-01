package com.nezhitsya.book.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.model.Comments
import java.text.DateFormat
import java.text.SimpleDateFormat

class BookDetailAdapter(val context: Context, private val commentList: ArrayList<Comments>): RecyclerView.Adapter<BookDetailAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        private var time = itemView?.findViewById<TextView>(R.id.time)
        private var comment = itemView?.findViewById<TextView>(R.id.comment)

        @SuppressLint("SimpleDateFormat")
        fun bind(mComment: Comments) {
            comment?.text = mComment.comment.toString()

            val df: DateFormat = SimpleDateFormat("yy.MM.dd  hh:mm")
            time?.text = df.format(mComment.time)
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentList[position])
    }

}