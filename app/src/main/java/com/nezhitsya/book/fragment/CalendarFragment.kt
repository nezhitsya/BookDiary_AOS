package com.nezhitsya.book.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.MainActivity
import com.nezhitsya.book.R
import com.nezhitsya.book.adapter.CalendarAdapter
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment(index: Int) : Fragment() {
    lateinit var mContext: Context
    lateinit var mActivity: MainActivity
    lateinit var currentDate: Date
    lateinit var yearmonthText: TextView
    lateinit var calendarLayout: LinearLayout
    lateinit var calendarView: RecyclerView
    lateinit var calendarAdapter: CalendarAdapter

    var pageIndex = index

    companion object {
        var instance: CalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) {
            mContext = context
            mActivity = activity as MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_calendar, container, false)

        initView(view)
        initCalendar()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initView(view: View) {
        pageIndex -= (Int.MAX_VALUE / 2)

        yearmonthText = view.year_month_text
        calendarLayout = view.calendar_layout
        calendarView = view.recycler_view

        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date

        var dateTime: String = SimpleDateFormat(mContext.getString(R.string.calendar_year_month_format), Locale.KOREA).format(date.time)
        yearmonthText.setText(dateTime)
    }

    fun initCalendar() {
        calendarAdapter = CalendarAdapter(mContext, calendarLayout, currentDate)
        calendarView.adapter = calendarAdapter
        calendarView.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendarView.setHasFixedSize(true)
        calendarAdapter.itemClick = object : CalendarAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val firstDateIndex = calendarAdapter.dataList.indexOf(calendarAdapter.calendarDate.currentMaxDate)
                val lastDateIndex = calendarAdapter.dataList.lastIndexOf(calendarAdapter.calendarDate.currentMaxDate)

                if (position < firstDateIndex || position > lastDateIndex) {
                    return
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

}