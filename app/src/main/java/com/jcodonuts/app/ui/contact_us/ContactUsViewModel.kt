package com.jcodonuts.app.ui.contact_us

import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.ProfileMenu
import com.jcodonuts.app.data.local.ProfileMenuHeader
import com.jcodonuts.app.data.local.SocmedMenu
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.ui.main.profile.ProfileControllerListener
import javax.inject.Inject

class ContactUsViewModel @Inject constructor(): ProfileControllerListener, BaseViewModel() {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    fun loadData(){
        datas.value?.let {
            it.add(ProfileMenuHeader("Contact Us"))
            it.add(ProfileMenu(R.drawable.ic_baseline_language, "www.jcodonuts.com", WEB))
            it.add(ProfileMenu(R.drawable.ic_mail, "jcodonuts@maill.com", EMAIL))
            it.add(ProfileMenu(R.drawable.ic_call, "0888 7182 8721", PHONE))
            it.add(ProfileMenuHeader("Social Media"))
            it.add(SocmedMenu(R.drawable.ic_facebook, "iamjcolovers", FACEBOOK))
            it.add(SocmedMenu(R.drawable.ic_instagram, "jcoindonesia", INSTAGRAM))
            it.add(SocmedMenu(R.drawable.ic_youtube, "J.CO Donuts & Coffee", YOUTUBE))
            datas.postValue(it)
        }
    }

    override fun onMenuClick(index: Int) {
        datas.value?.let {
//            val data = it[index] as ProfileMenu
//            _showMenuDetail.value = SingleEvents(data.type)
        }
    }

    override fun onSignOut() {
        
    }

    override fun onEditProfile() {

    }

    override fun onLinkAjaClick() {

    }

    companion object{
        const val WEB = 1
        const val EMAIL = 2
        const val PHONE = 3
        const val FACEBOOK = 4
        const val INSTAGRAM = 5
        const val YOUTUBE = 5
    }
}