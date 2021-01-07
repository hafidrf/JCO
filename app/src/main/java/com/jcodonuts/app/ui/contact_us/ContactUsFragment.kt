package com.jcodonuts.app.ui.contact_us

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentContactUsBinding
import com.jcodonuts.app.databinding.FragmentMainProfileBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import javax.inject.Inject

class ContactUsFragment @Inject constructor() : BaseFragment<FragmentContactUsBinding, ContactUsViewModel>() {
    private val TAG = "ProfileFragment"

    override fun getViewModelClass(): Class<ContactUsViewModel> {
        return ContactUsViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_contact_us
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
        val controller = ContactUsController(viewModel)
        binding.recyclerview.setController(controller)
        viewModel.datas.observe(this,  {
            controller.setData(it)
        })
    }

    private fun initObserver(){

    }
}