package com.jcodonuts.app.ui.main.notification

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainNotificationBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import javax.inject.Inject

class NotificationFragment @Inject constructor() : BaseFragment<FragmentMainNotificationBinding, NotificationViewModel>() {

    override fun getViewModelClass(): Class<NotificationViewModel> {
        return NotificationViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_notification
    }

    override fun onViewReady(savedInstance: Bundle?) {
        initActionBar()
        initRecyclerview()

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }
    }

    private fun initActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }

    private fun initRecyclerview(){
        val controller = NotificationController(viewModel)
        binding.recyclerview.setController(controller)
        viewModel.datas.observe(this,  {
            controller.data = it
        })
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}