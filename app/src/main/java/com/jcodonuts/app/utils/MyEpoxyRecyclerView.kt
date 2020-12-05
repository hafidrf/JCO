package com.jcodonuts.app.utils

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import com.airbnb.epoxy.EpoxyRecyclerView

class MyEpoxyRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : EpoxyRecyclerView(context, attrs, defStyleAttr) {

    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        val childCount = childCount
        for (index in 0 until childCount) getChildAt(index).dispatchApplyWindowInsets(insets) // let children know about WindowInsets
        return insets!!
    }
}