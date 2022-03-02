package com.nezhitsya.book.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nezhitsya.book.R
import com.squareup.picasso.Picasso
import android.content.DialogInterface

import com.google.firebase.database.FirebaseDatabase

import android.widget.LinearLayout

import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.viewModel.BookDetailViewModel

class BookDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_book_detail, container, false)

        var image: ImageView = view.findViewById(R.id.cover_image)
        var title: TextView = view.findViewById(R.id.title)
        var author: TextView = view.findViewById(R.id.author)
        var description: TextView = view.findViewById(R.id.description)
        var write: TextView = view.findViewById(R.id.write_btn)
        var comment: TextView = view.findViewById(R.id.comment_btn)

        Picasso.get().load(requireArguments().getString("image")).into(image)
        title.text = requireArguments().getString("title")
        author.text = requireArguments().getString("author")
        description.text = requireArguments().getString("description")

        write.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container, WriteFragment()).addToBackStack(null).commit()
        }

        comment.setOnClickListener {
            writeComment(requireArguments().getString("title").toString())
        }

        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        viewModel.getComment(requireArguments().getString("title").toString(),
            requireContext(), recyclerView)

        return view
    }

    private fun writeComment(title: String) {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle("한줄평 남기기")

        val editText = EditText(context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        editText.layoutParams = lp
        dialog.setView(editText)

        dialog.setPositiveButton("작성하기") { dialog, which ->
            viewModel.writeComment(title, editText.text.toString())
        }
        dialog.setNegativeButton("취소하기") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

}