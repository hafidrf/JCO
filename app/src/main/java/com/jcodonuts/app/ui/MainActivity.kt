package com.jcodonuts.app.ui

import android.os.Bundle
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.ActivityMainBinding
import com.jcodonuts.app.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding> (){

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}