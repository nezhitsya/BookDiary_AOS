package com.nezhitsya.book.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.MainActivity
import com.nezhitsya.book.R
import com.nezhitsya.book.fragment.SearchFragment
import com.nezhitsya.book.model.CalendarDate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(val context: Context, private val calendarLayout: LinearLayout, private val date: Date): RecyclerView.Adapter<CalendarAdapter.Holder>() {
    var dataList: ArrayList<Int> = arrayListOf()
    var itemClick: ItemClick? = null
    var calendarDate: CalendarDate = CalendarDate(date)

    init {
        calendarDate.initBaseCalendar()
        dataList = calendarDate.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val height = calendarLayout.height / 6
        holder.itemView.layoutParams.height = height

        holder.bind(dataList[position], position, context)

        if (itemClick != null) {
            holder.itemView.setOnClickListener {
                val fragment = (context as MainActivity).supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container, SearchFragment()).addToBackStack(null).commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return Holder(view)
    }

    inner class Holder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var day = itemView?.findViewById<TextView>(R.id.day)
        var coverImage = itemView?.findViewById<ImageView>(R.id.cover_image)

        fun bind(date: Int, position: Int, context: Context) {
            val firstDateIndex = calendarDate.prevTail
            val lastDateIndex = dataList.size - calendarDate.nextHead - 1
            day!!.text = date.toString()

            val dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            val dateInt = dateString.toInt()
            if (dataList[position] == dateInt) {
                day!!.setTypeface(day!!.typeface, Typeface.BOLD)
            }

            if (position < firstDateIndex || position > lastDateIndex) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    day!!.setTextAppearance(R.style.LightColorTextViewStyle)
                }
            }
        }
    }

}