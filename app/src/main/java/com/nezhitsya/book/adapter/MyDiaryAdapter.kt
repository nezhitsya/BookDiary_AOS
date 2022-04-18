package com.nezhitsya.book.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.model.Diary

class MyDiaryAdapter(val context: Context, val diaryList: ArrayList<Diary>): RecyclerView.Adapter<MyDiaryAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDiaryAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.count()
    }

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var title = itemView?.findViewById<TextView>(R.id.title)
        var desc = itemView?.findViewById<TextView>(R.id.desc)
        var date = itemView?.findViewById<TextView>(R.id.date)
        var image = itemView?.findViewById<ImageView>(R.id.cover_image)

        fun bind(mDiary: Diary, context: Context) {

        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(diaryList[position], context)
    }

}