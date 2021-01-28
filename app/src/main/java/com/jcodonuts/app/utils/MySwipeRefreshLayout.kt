package com.jcodonuts.app.utils

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.LinearLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MySwipeRefreshLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {

    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        val childCount = childCount
        for (index in 0 until childCount) getChildAt(index).dispatchApplyWindowInsets(insets) // let children know about WindowInsets
        return insets!!
    }
}