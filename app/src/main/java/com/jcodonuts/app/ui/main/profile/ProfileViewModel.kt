package com.jcodonuts.app.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class ProfileViewModel @Inject constructor(): ProfileControllerListener, BaseViewModel() {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    private val _showEditProfile = MutableLiveData<SingleEvents<String>>()
    val showEditProfile : LiveData<SingleEvents<String>>
        get() = _showEditProfile

    private val _showMenuDetail = MutableLiveData<SingleEvents<String>>()
    val showMenuDetail : LiveData<SingleEvents<String>>
        get() = _showMenuDetail

    private val _signOut = MutableLiveData<SingleEvents<String>>()
    val signOut : LiveData<SingleEvents<String>>
        get() = _signOut

    init {
        datas.value = mutableListOf()
    }

    fun loadData(){
        datas.value?.let {
            it.add(ProfileHeader("https://drive.google.com/uc?id=1gtrQXUFKsrkexSwWA9eN3Mh2Xtau3S3p", "Farriza Ahmad", "Rp. 100.000", "5.248"))
            it.add(ProfileMenuHeader("Account"))
            it.add(ProfileMenu(R.drawable.ic_lock, "Change Password"))
            it.add(ProfileMenu(R.drawable.ic_orders, "Order"))
            it.add(ProfileMenu(R.drawable.ic_baseline_language, "Language"))
            it.add(ProfileMenu(R.drawable.ic_call, "Contact Us"))
            it.add(ProfileMenuHeader("General"))
            it.add(ProfileMenu(R.drawable.ic_term, "Terms & Conditions"))
            it.add(ProfileMenu(R.drawable.ic_privacy, "Privacy Policy"))
            it.add(ProfileFooter(""))
            datas.postValue(it)
        }
    }

    override fun onMenuClick(index: Int) {
        
    }

    override fun onSignOut() {
        
    }

    override fun onEditProfile() {
        
    }

    override fun onLinkAjaClick() {
        
    }
}