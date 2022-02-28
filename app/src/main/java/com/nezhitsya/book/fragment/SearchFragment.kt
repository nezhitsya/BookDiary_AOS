package com.nezhitsya.book.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nezhitsya.book.R
import com.nezhitsya.book.adapter.SearchAdapter
import com.nezhitsya.book.api.NetworkService
import com.nezhitsya.book.model.APIResult
import com.nezhitsya.book.utils.StripHTML.Companion.stripHtmlTag
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<APIResult>
    private lateinit var adapter: SearchAdapter

    var startResult: Int = 1
    var display: Int = 10
    var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_search, container, false)

        var bundle = Bundle()
        var search: EditText = view.findViewById(R.id.search_bar)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager

        search.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.clear()
                searchBook(s.toString().toLowerCase(), startResult, display)

                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val lastItemPosition = (recyclerView.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                        if (!recyclerView.canScrollVertically(1) && lastItemPosition == itemTotalCount) {
                            if (adapter.itemCount < total) {
                                searchBook(s.toString().toLowerCase(), ++startResult * 10, display)
                            }
                        }
                    }
                })
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        adapter = SearchAdapter()
        recyclerView.adapter = adapter

        adapter.setItemClickListener { item ->
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BookDetailFragment().apply {
                arguments = bundle.apply {
                    putString("title", stripHtmlTag(item.title))
                    putString("author", stripHtmlTag(item.author))
                    putString("image", stripHtmlTag(item.image))
                    putString("description", stripHtmlTag(item.description))
                }
            }).addToBackStack(null).commit()
        }

        return view
    }

    private fun searchBook(s: String, start: Int, display: Int) {
        val select = NetworkService.naverAPI
        call = select.getSearchBook(s, start, display)
        call.enqueue(object : Callback<APIResult> {
            override fun onResponse(call: Call<APIResult>, response: Response<APIResult>) {
                with(response) {
                    val body = body()
                    total = body!!.total

                    if (isSuccessful && body != null) {
                        body.items.let { adapter.setItems(it) }
                    }
                }
            }

            override fun onFailure(call: Call<APIResult>, t: Throwable) {

            }
        })
    }

}