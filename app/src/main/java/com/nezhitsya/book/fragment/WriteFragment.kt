package com.nezhitsya.book.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.nezhitsya.book.R
import com.nezhitsya.book.viewModel.WriteViewModel

class WriteFragment : Fragment() {

    private lateinit var viewModel: WriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_write, container, false)

        var save: ImageView = view.findViewById(R.id.save)
        var photo: ImageView = view.findViewById(R.id.photo)
        var title: EditText = view.findViewById(R.id.title)
        var desc: EditText = view.findViewById(R.id.description)

        viewModel = ViewModelProvider(this).get(WriteViewModel::class.java)

        save.setOnClickListener {  }
        photo.setOnClickListener {  }

        return view
    }

}