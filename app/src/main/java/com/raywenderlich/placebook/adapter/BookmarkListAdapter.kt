package com.raywenderlich.placebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.placebook.R
import com.raywenderlich.placebook.databinding.BookmarkItemBinding
import com.raywenderlich.placebook.ui.MapsActivity
import com.raywenderlich.placebook.viewmodel.MapsViewModel

// 1
class BookmarkListAdapter(
    private var bookmarkData: List<MapsViewModel.BookmarkView>?,
    private val mapsActivity: MapsActivity
) : RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {
    // 2
    class ViewHolder(
        val binding: BookmarkItemBinding,
        private val mapsActivity: MapsActivity
    ) : RecyclerView.ViewHolder(binding.root) {
    }
    // 3
    fun setBookmarkData(bookmarks: List<MapsViewModel.BookmarkView>) {
        this.bookmarkData = bookmarks
        notifyDataSetChanged()
    }
    // 4
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookmarkItemBinding.inflate(layoutInflater,
            parent, false)
        return ViewHolder(binding, mapsActivity)
    }
    override fun onBindViewHolder(holder: ViewHolder, position:
    Int) {
// 5
        bookmarkData?.let { list->
            val bookmarkViewData = list[position]
            // 7
            holder.binding.root.tag = bookmarkViewData
            holder.binding.bookmarkData = bookmarkViewData
            bookmarkViewData.categoryResourceId?.let {
                holder.binding.bookmarkIcon.setImageResource(it)
            }
        }
    }
    // 8
    override fun getItemCount() = bookmarkData?.size ?: 0

    init {
        binding.root.setOnClickListener {
            val bookmarkView = itemView.tag as MapsViewModel.BookmarkView
            mapsActivity.moveToBookmark(bookmarkView)
        }
    }
}