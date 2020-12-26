package com.jcodonuts.app.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.ActivityMainBinding
import com.jcodonuts.app.databinding.FragmentChangePasswordBinding
import com.jcodonuts.app.ui.base.BaseActivity

class TestingActivity : BaseActivity<FragmentChangePasswordBinding> (){

    override fun getLayoutId(): Int {
        return R.layout.fragment_change_password
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}