package com.jcodonuts.app.ui.product_detail

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentProductDetailBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class ProductDetailFragment @Inject constructor() : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>() {


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



        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.loadDetail()
        }
    }

    private fun initRecyclerview() {
        val controller = ProductDetailController(viewModel)
        binding.recyclerview.setController(controller)

        viewModel.datas.observe(this,  {
            controller.data = it
        })

//        viewModel.notifyItemUpdate.observe(this, {
//            it.getContentIfNotHandled()?.let { position ->
//                adapter.notifyItemChanged(position)
//            }
//        })
//
//        viewModel.notifyContentUpdate.observe(this, {
//            it.getContentIfNotHandled()?.let { position ->
//                adapter.notifyItemChanged(position)
//            }
//        })
    }
}