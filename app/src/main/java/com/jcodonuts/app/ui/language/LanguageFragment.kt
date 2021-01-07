package com.jcodonuts.app.ui.language

import android.os.Bundle
import android.view.WindowManager
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentChangePasswordBinding
import com.jcodonuts.app.databinding.FragmentLanguageBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.KeyboardUtil
import javax.inject.Inject

class LanguageFragment @Inject constructor(): BaseFragment<FragmentLanguageBinding, LanguageViewModel>() {

    override fun getViewModelClass(): Class<LanguageViewModel> {
        return LanguageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_language
    }

    override fun onViewReady(savedInstance: Bundle?) {

    }
}