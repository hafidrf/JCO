package com.jcodonuts.app.data.local

import com.jcodonuts.app.utils.recycler_adapter.RecyclerItem
import com.jcodonuts.app.R
import com.jcodonuts.app.ui.base.BaseModel

data class Divider16(val temp:String=""):BaseCell(), BaseModel{
    override fun layoutId(): Int {
        return R.layout.view_holder_divider16
    }
}
