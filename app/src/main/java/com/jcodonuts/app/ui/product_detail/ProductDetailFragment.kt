package com.jcodonuts.app.ui.product_detail

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentProductDetailBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.zzexample.article.MenuAdapter
import com.jcodonuts.app.utils.recycler_adapter.DataBindingRecyclerAdapter
import javax.inject.Inject

class ProductDetailFragment @Inject constructor() : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>() {

    private lateinit var adapter: ProductDetailAdapter

    override fun getViewModelClass(): Class<ProductDetailViewModel> {
        return ProductDetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_product_detail
    }

    override fun onViewReady(savedInstance: Bundle?) {
        binding.btnBack.setOnClickListener {
            onBackPress()
        }

//        val controller = ProductDetailController(viewModel)
//
//        binding.homeRecyclerview.setController(controller)

        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.loadDetail()
        }
    }

    private fun initRecyclerview() {
        adapter = ProductDetailAdapter(viewModel)
        binding.recyclerview.adapter = adapter

        viewModel.datas.observe(this,  {
            adapter.submitList(it)
        })

        viewModel.notifyItemUpdate.observe(this, {
            it.getContentIfNotHandled()?.let { position ->
                adapter.notifyItemChanged(position)
            }
        })
    }
}