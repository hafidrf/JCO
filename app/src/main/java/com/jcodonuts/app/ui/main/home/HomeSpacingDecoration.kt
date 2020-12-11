package com.jcodonuts.app.ui.main.home

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class HomeSpacingDecoration : ItemDecoration() {
    private val TAG = "HomeSpacingDecoration"
    private var outerPadding = -1
    private var innerPadding = -1
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (outerPadding == -1 || innerPadding == -1) {
            val m: DisplayMetrics = view.resources.displayMetrics
            outerPadding = TypedValue.applyDimension(COMPLEX_UNIT_DIP, OUTER_PADDING_DP.toFloat(), m).toInt()
            innerPadding = TypedValue.applyDimension(COMPLEX_UNIT_DIP, INNER_PADDING_DP.toFloat(), m).toInt()
        }
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as GridLayoutManager?
        val spanSizeLookup = layoutManager!!.spanSizeLookup

        // Zero everything out for the common case
        outRect.setEmpty()
        val spanSize = spanSizeLookup.getSpanSize(position)
        val spanCount = layoutManager.spanCount
        val spanIndex = spanSizeLookup.getSpanIndex(position, spanCount)
//        Log.d(TAG, "$spanSize      $spanCount    $spanIndex");
        when {
            spanSize == 1  && spanIndex==0 -> {
                // Only item in row
                outRect.left = outerPadding
                outRect.right = innerPadding
            }
            spanSize == 1  && spanIndex==1 -> {
                // Only item in row
                outRect.left = innerPadding
                outRect.right = outerPadding
            }
            else -> {

            }
        }
    }

    companion object {
        private const val OUTER_PADDING_DP = 16
        private const val INNER_PADDING_DP = 8
    }
}