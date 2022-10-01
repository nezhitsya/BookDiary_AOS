package com.nezhitsya.book.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.nezhitsya.book.MainActivity
import com.nezhitsya.book.R
import com.nezhitsya.book.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var mContext: Context
    private lateinit var calendarViewPager: ViewPager2

    private var instance: HomeFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        calendarViewPager = view.calendarViewPager

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val homeAdapter = HomeAdapter(requireActivity())
        calendarViewPager.adapter = homeAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        homeAdapter.apply { calendarViewPager.setCurrentItem(this.firstFragmentPosition, false) }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

}