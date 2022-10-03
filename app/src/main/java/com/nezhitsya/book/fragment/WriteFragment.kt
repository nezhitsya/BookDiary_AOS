package com.nezhitsya.book.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.nezhitsya.book.R
import com.nezhitsya.book.viewModel.WriteViewModel
import java.util.*

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class WriteFragment : Fragment() {

    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var viewModel: WriteViewModel
    private lateinit var dateBtn: TextView

    private var stars = 0

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_write, container, false)

        viewModel = ViewModelProvider(this)[WriteViewModel::class.java]

        dateBtn = view.findViewById(R.id.date)
        val title: TextView = view.findViewById(R.id.title)
        val star1: ImageView = view.findViewById(R.id.star1)
        val star2: ImageView = view.findViewById(R.id.star2)
        val star3: ImageView = view.findViewById(R.id.star3)
        val coverImage: ImageView = view.findViewById(R.id.cover_image)
        val desc: EditText = view.findViewById(R.id.description)
        val save: TextView = view.findViewById(R.id.save)
        val cancel: TextView = view.findViewById(R.id.cancel)

        updateLabel()

        val date = OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        val imageUrl: String = requireArguments().getString("image") ?: ""

        dateBtn.setOnClickListener {
            DatePickerDialog(requireContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        star1.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star_full)
            star2.setImageResource(R.drawable.ic_star_empty)
            star3.setImageResource(R.drawable.ic_star_empty)
            stars = 1
        }

        star2.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star_full)
            star2.setImageResource(R.drawable.ic_star_full)
            star3.setImageResource(R.drawable.ic_star_empty)
            stars = 2
        }

        star3.setOnClickListener {
            star1.setImageResource(R.drawable.ic_star_full)
            star2.setImageResource(R.drawable.ic_star_full)
            star3.setImageResource(R.drawable.ic_star_full)
            stars = 3
        }

        save.setOnClickListener {
            viewModel.writeDiary(title.text.toString(), imageUrl, desc.text.toString(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), stars)
            requireActivity().supportFragmentManager.popBackStack()
        }

        cancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        Picasso.get().load(imageUrl).into(coverImage)
        title.text = requireArguments().getString("title")

        return view
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateLabel() {
        val myFormat = "yyyy.MM.dd"
        val dateFormat = SimpleDateFormat(myFormat)
        dateBtn.text = dateFormat.format(calendar.time)
    }

}