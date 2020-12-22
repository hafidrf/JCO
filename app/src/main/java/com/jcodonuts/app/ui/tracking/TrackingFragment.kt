package com.jcodonuts.app.ui.tracking

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentPaymentTopupBinding
import com.jcodonuts.app.databinding.FragmentTrackingBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import javax.inject.Inject

class TrackingFragment @Inject constructor() : BaseFragment<FragmentTrackingBinding, TrackingViewModel>() {

    override fun getViewModelClass(): Class<TrackingViewModel> {
        return TrackingViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tracking
    }

    override fun onViewReady(savedInstance: Bundle?) {
        setupActionBar()
        initObserver()
        initRecycler()

        binding.refresh.setOnRefreshListener {
            Handler().postDelayed({ binding.refresh.isRefreshing = false },2000)
        }


    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }

    private fun initRecycler(){
        val controller = TrackingController(viewModel)
        binding.recyclerView.setController(controller)

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        viewModel.datas.observe(this, {
            controller.data = it
        })
    }

    private fun initObserver(){
        viewModel.showDlgSuccessOrder.observe(this, {
            it.getContentIfNotHandled()?.let {
                val dlg = DialogJco(requireContext())
                dlg.showPopup(
                        "Order Completed!",
                        "Your order was successful, thank you for using the jco apps",
                        "Order again",
                        View.OnClickListener{
                            dlg.dismissPopup()
                            navigateTo(R.string.linkMainFragment)
                        })
            }
        })
    }
}