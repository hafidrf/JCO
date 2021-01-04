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

    private val _showMenuDetail = MutableLiveData<SingleEvents<Int>>()
    val showMenuDetail : LiveData<SingleEvents<Int>>
        get() = _showMenuDetail

    private val _showLinkAja = MutableLiveData<SingleEvents<String>>()
    val showLinkAja : LiveData<SingleEvents<String>>
        get() = _showLinkAja

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
            it.add(ProfileMenu(R.drawable.ic_lock, "Change Password", CHANGE_PASSWORD))
            it.add(ProfileMenu(R.drawable.ic_orders, "Order", ORDER))
            it.add(ProfileMenu(R.drawable.ic_baseline_language, "Language", LANGUAGE))
            it.add(ProfileMenu(R.drawable.ic_call, "Contact Us", CONTACT_US))
            it.add(ProfileMenuHeader("General"))
            it.add(ProfileMenu(R.drawable.ic_term, "Terms & Conditions", TERM_CONDITION))
            it.add(ProfileMenu(R.drawable.ic_privacy, "Privacy Policy", PRIVACY_POLICY))
            it.add(ProfileFooter(""))
            datas.postValue(it)
        }
    }

    override fun onMenuClick(index: Int) {
        datas.value?.let {
            val data = it[index] as ProfileMenu
            _showMenuDetail.value = SingleEvents(data.type)
        }
    }

    override fun onSignOut() {
        
    }

    override fun onEditProfile() {
        _showEditProfile.value = SingleEvents("edit_profile")
    }

    override fun onLinkAjaClick() {
        _showLinkAja.value = SingleEvents("show_linkaja")
    }

    companion object{
        const val CHANGE_PASSWORD = 1
        const val ORDER = 2
        const val LANGUAGE = 3
        const val CONTACT_US = 4
        const val TERM_CONDITION = 5
        const val PRIVACY_POLICY = 6
    }
}