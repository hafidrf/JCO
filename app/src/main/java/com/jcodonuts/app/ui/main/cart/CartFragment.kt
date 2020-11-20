package com.jcodonuts.app.ui.main.cart

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
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

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}