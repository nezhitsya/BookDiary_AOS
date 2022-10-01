package com.nezhitsya.book.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.model.Books
import com.squareup.picasso.Picasso

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.Holder>() {

    private lateinit var callback: (Books) -> Unit
    private val items: ArrayList<Books> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return Holder(view).also {
            it.itemView.setOnClickListener { _ ->
                callback(items[it.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        private var coverImage = itemView?.findViewById<ImageView>(R.id.cover_image)

        fun bind(book: Books) {
            with(book) {
                Picasso.get().load(image).into(coverImage)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Books>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemClickListener(callback: (Books) -> Unit) {
        this.callback = callback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}