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
        initRecyclerview()
        initObserver()

        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }

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

    private fun initObserver(){
        viewModel.showDelivery.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkDeliveryFragment)
            }
        })

        viewModel.showPickup.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkPickupFragment)
            }
        })

        viewModel.showChangePayment.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkChoosePaymentFragment)
            }
        })

        viewModel.showPaymentDetail.observe(this, {
            it.getContentIfNotHandled()?.let {
                navigateTo(R.string.linkPaymentDetailFragment)
            }
        })

        viewModel.showDialogNote.observe(this, {
            it.getContentIfNotHandled()?.let {
                val dlg = DialogNote()
                dlg.showDialog(requireActivity().supportFragmentManager, "CartFragment", object : DialogNote.OnDialogClickListener {
                    override fun onSaveClick(notes: String) {
                        dlg.dissmissDialog()
                    }
                })
            }
        })
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}