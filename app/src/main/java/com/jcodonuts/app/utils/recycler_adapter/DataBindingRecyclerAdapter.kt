package com.jcodonuts.app.utils.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter

class DataBindingRecyclerAdapter : ListAdapter<RecyclerItem, BindingViewHolder>(
    DiffCallback()
) {
    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder,
        position: Int
    ) {
        holder.run {
            getItem(position).bind(binding)
            if (binding.hasPendingBindings()) {
                binding.executePendingBindings()
            }
        }
    }
}

private fun RecyclerItem.bind(binding: ViewDataBinding) {
    variableId.forEachIndexed { index, i ->
        val isVariableFound = binding.setVariable(i, data[index])
        if (isVariableFound.not()) {
            throw IllegalStateException(
                buildErrorMessage(i, binding)
            )
        }
    }
}

private fun buildErrorMessage(
    variableId: Int,
    binding: ViewDataBinding
): String {
    val variableName = DataBindingUtil.convertBrIdToString(variableId)
    val className = binding::class.simpleName
    return "Failed to find variable='$variableName' in the following databinding layout: $className"
}
