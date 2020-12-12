package com.jcodonuts.app.ui.main.cart

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import com.jcodonuts.app.ui.main.home.HomeController
import com.jcodonuts.app.ui.main.home.HomeSpacingDecoration
import javax.inject.Inject

class CartFragment @Inject constructor() : BaseFragment<FragmentMainCartBinding, CartViewModel>() {


    override fun getViewModelClass(): Class<CartViewModel> {
        return CartViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_cart
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }
    }

    private fun initRecyclerview(){
        val controller = CartController(viewModel)
        binding.recyclerView.setController(controller)
        viewModel.datas.observe(this,  {
            controller.setData(it)
        })
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}