package com.jcodonuts.app.ui.mainNotification

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentMainNotificationBinding
import com.jcodonuts.app.ui.base.BaseFragment
import javax.inject.Inject

class NotificationFragment @Inject constructor() : BaseFragment<FragmentMainNotificationBinding, NotificationViewModel>() {

    override fun getViewModelClass(): Class<NotificationViewModel> {
        return NotificationViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_notification
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}