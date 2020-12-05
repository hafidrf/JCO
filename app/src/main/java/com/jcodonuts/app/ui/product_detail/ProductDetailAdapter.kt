package com.jcodonuts.app.ui.product_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.data.local.ProductDetailContent
import com.jcodonuts.app.data.local.ProductDetailDonut
import com.jcodonuts.app.databinding.ViewHolderDivider16Binding
import com.jcodonuts.app.databinding.ViewHolderProductDetailContentsBinding
import com.jcodonuts.app.databinding.ViewHolderProductDetailDonutItemBinding
import com.jcodonuts.app.ui.base.BaseModel

import javax.inject.Inject

class ProductDetailAdapter @Inject constructor(val onItemClick : ViewHolderListener) :
    ListAdapter<BaseModel,  RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder(
            DiffCallback
        ).build()
    ) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.view_holder_product_detail_contents -> {
                val binding = DataBindingUtil.inflate<ViewHolderProductDetailContentsBinding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
                return ViewHolderContent(binding, onItemClick)
            }
            R.layout.view_holder_product_detail_donut_item -> {
                val binding = DataBindingUtil.inflate<ViewHolderProductDetailDonutItemBinding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
                return ViewHolderDonut(binding, onItemClick)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ViewHolderDivider16Binding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
                return ViewHolderDivider16(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.view_holder_product_detail_contents -> {
                val vHolder = holder as ViewHolderContent
                vHolder.bind(getItem(position) as ProductDetailContent, position)
            }
            R.layout.view_holder_product_detail_donut_item -> {
                val vHolder = holder as ViewHolderDonut
                vHolder.bind(getItem(position) as ProductDetailDonut, position)
            }
            else -> {
                val vHolder = holder as ViewHolderDivider16
                vHolder.bind(getItem(position) as Divider16)
            }
        }
    }

    class ViewHolderContent(var binding: ViewHolderProductDetailContentsBinding, val onItemClick : ViewHolderListener) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductDetailContent, pos:Int) {
            binding.data = item
            binding.onClickListener = onItemClick
            binding.position = pos
            binding.executePendingBindings()
        }
    }

    class ViewHolderDonut(var binding: ViewHolderProductDetailDonutItemBinding, val onItemClick : ViewHolderListener) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductDetailDonut, pos:Int){
            binding.data = item
            binding.onClickListener = onItemClick
            binding.position = pos
            binding.executePendingBindings()
        }
    }

    class ViewHolderDivider16(var binding: ViewHolderDivider16Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Divider16){
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<BaseModel>(){
    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

}