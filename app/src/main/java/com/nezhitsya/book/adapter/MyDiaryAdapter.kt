package com.nezhitsya.book.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.model.Diary
import com.squareup.picasso.Picasso

class MyDiaryAdapter(val context: Context, private val diaryList: ArrayList<Diary>): RecyclerView.Adapter<MyDiaryAdapter.Holder>() {

    private lateinit var callback: (Diary) -> Unit
    private val items: ArrayList<Diary> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDiaryAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_item, parent, false)
        return Holder(view).also {
            it.itemView.setOnClickListener { _ ->
                callback(items[it.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int {
        return diaryList.count()
    }

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var title = itemView?.findViewById<TextView>(R.id.title)
        var desc = itemView?.findViewById<TextView>(R.id.desc)
        var date = itemView?.findViewById<TextView>(R.id.date)
        var coverImage = itemView?.findViewById<ImageView>(R.id.cover_image)

        @SuppressLint("SetTextI18n")
        fun bind(diary: Diary, context: Context) {
            with(diary) {
                Picasso.get().load(image).into(coverImage)
            }

            title!!.text = diary.title
            desc!!.text = diary.desc
            date!!.text = "${diary.year}년 ${diary.month}월 ${diary.day}일"
        }
    }

}