package com.jcodonuts.app.ui.product_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.jcodonuts.app.R
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.databinding.FragmentProductDetailBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.SchedulerProvider
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

        binding.viewModel = viewModel
        binding.executePendingBindings()

        initRecyclerview()
        initObserver()

        if(!isFragmentFromPaused){
            arguments?.let {
                it.getString("id")?.let { it1 -> viewModel.loadDetail(it1) }
            }
        }
    }

    private fun initRecyclerview() {
        val controller = ProductDetailController(viewModel)
        binding.recyclerview.setController(controller)

        viewModel.datas.observe(this,  {
            controller.data = it
        })


//
//        viewModel.notifyContentUpdate.observe(this, {
//            it.getContentIfNotHandled()?.let { position ->
//                adapter.notifyItemChanged(position)
//            }
//        })
    }

    private fun initObserver(){
        viewModel.addToCart.observe(this, {
            it.getContentIfNotHandled()?.let { _ ->
                val dlg = DialogJco(requireContext())
                dlg.showPopup(
                        "Product added successfully",
                        "Golden Nastar already in into the cart and ready to take home",
                        "My order",
                        View.OnClickListener{
                            dlg.dismissPopup()
                            val action = ProductDetailFragmentDirections.actionFromProductToMainFragment("cart")
                            findNavController()
                                    .navigate(action,
                                            FragmentNavigator.Extras.Builder()
                                                    .build())
                        })

            }
        })
    }
}