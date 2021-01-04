package com.jcodonuts.app.ui.edit_profile

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.jcodonuts.app.R
import com.jcodonuts.app.databinding.FragmentEditProfileBinding
import com.jcodonuts.app.databinding.FragmentHotPromoBinding
import com.jcodonuts.app.databinding.FragmentPaymentTopupBinding
import com.jcodonuts.app.databinding.FragmentTrackingBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.utils.DialogJco
import com.jcodonuts.app.utils.KeyboardUtil
import javax.inject.Inject

class EditProfileFragment @Inject constructor() : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override fun getViewModelClass(): Class<EditProfileViewModel> {
        return EditProfileViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_edit_profile
    }

    override fun onViewReady(savedInstance: Bundle?) {
        KeyboardUtil(requireActivity(), binding.root)
        setupActionBar()
        initObserver()

        if(!isFragmentFromPaused){
            viewModel.loadData()
        }

        binding.btnBirthDate.setOnClickListener {
            DialogBirtDate().showDialog(requireActivity().supportFragmentManager, object : DialogBirtDate.OnDialogClickListener{
                override fun onChange() {

                }
            })
        }
        binding.btnGender.setOnClickListener {
            DialogGender().showDialog(requireActivity().supportFragmentManager, object : DialogGender.OnDialogClickListener{
                override fun onChange() {

                }
            })
        }
    }

    private fun setupActionBar(){
        binding.topBar.btnBack.setOnClickListener {
            onBackPress()
        }
    }

    private fun initObserver(){
        viewModel.datas.observe(this, {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        })
    }
}