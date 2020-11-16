package com.jcodonuts.app.ui.mainCart

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class CartFragment @Inject constructor() : BaseFragment<FragmentMainCartBinding, CartViewModel>() {

    override fun getViewModelClass(): Class<CartViewModel> {
        return CartViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_cart
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}