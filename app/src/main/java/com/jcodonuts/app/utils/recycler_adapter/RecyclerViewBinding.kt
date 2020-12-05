package com.jcodonuts.app.utils.recycler_adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun setRecyclerViewItems(
    recyclerView: RecyclerView,
    items: List<RecyclerItem>?
) {

    var adapter = (recyclerView.adapter as? DataBindingRecyclerAdapter)
    if (adapter == null) {
        adapter = DataBindingRecyclerAdapter()
        recyclerView.adapter = adapter
    }
    Log.d("setRecyclerViewItems", "MASUK "+adapter.itemCount.toString())

    adapter.submitList(
        items.orEmpty()
    )
}