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

    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }
}