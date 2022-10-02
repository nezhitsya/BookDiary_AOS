package com.nezhitsya.book.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.viewModel.MyDiaryViewModel

class MyDiaryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MyDiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_diary, container, false)

        viewModel = ViewModelProvider(this)[MyDiaryViewModel::class.java]

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        viewModel.getDiary(requireContext(), recyclerView)

        return view
    }

}