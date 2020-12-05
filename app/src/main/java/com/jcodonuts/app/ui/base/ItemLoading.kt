package com.jcodonuts.app.ui.base

import com.jcodonuts.app.R

data class ItemLoading(
    val temp: String
) : BaseModel {
    override fun layoutId(): Int {
        return R.layout.item_load_more
    }
}