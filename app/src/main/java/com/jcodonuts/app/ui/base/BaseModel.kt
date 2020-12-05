package com.jcodonuts.app.ui.base

import androidx.annotation.LayoutRes

interface BaseModel {

    @LayoutRes
    fun layoutId(): Int

    fun id(): Long = hashCode().toLong()
    fun areContentsTheSame(newItem: BaseModel): Boolean = toString() == newItem.toString()
}