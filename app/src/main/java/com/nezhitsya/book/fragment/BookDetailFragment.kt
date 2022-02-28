package com.nezhitsya.book.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nezhitsya.book.R
import com.squareup.picasso.Picasso

class BookDetailFragment : Fragment() {

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

        Picasso.get().load(requireArguments().getString("image")).into(image)
        title.text = requireArguments().getString("title")
        author.text = requireArguments().getString("author")
        description.text = requireArguments().getString("description")

        write.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container, WriteFragment()).addToBackStack(null).commit()
        }

        return view
    }

}