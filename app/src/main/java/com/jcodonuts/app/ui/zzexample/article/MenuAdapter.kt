package com.jcodonuts.app.ui.zzexample.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Country
import com.jcodonuts.app.databinding.ItemMenuBinding

import javax.inject.Inject

class MenuAdapter @Inject constructor(
    private val clickListener : OnMenuItemClickListener
) :
    ListAdapter<Country,  RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder(
            DiffItemBase
        ).build()
    ) {
    private val TAG = "MenuAdapter"

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMenuBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_menu,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder = holder as UserViewHolder
        vHolder.binding.lytMenuParent.setOnClickListener {
            clickListener.onClick(getItem(position))
        }
        vHolder.bind(getItem(position))
    }

    class UserViewHolder(var binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Country) {
            binding.country = item
            binding.executePendingBindings()
        }
    }

    interface OnMenuItemClickListener {
        fun onClick(country: Country)
    }
}

object DiffItemBase : DiffUtil.ItemCallback<Country>(){
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}