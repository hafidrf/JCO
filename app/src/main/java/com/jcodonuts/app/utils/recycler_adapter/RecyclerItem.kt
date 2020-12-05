package com.jcodonuts.app.utils.recycler_adapter

import androidx.annotation.LayoutRes

data class RecyclerItem(
    val data: List<Any>,
    @LayoutRes val layoutId: Int,
    val variableId: List<Int>
)

