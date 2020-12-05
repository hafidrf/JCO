package com.jcodonuts.app.utils.recycler_adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindingViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)