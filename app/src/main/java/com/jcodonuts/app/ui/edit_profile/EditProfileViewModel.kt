package com.jcodonuts.app.ui.edit_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.ProfileData
import com.jcodonuts.app.ui.base.BaseViewModel
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(): BaseViewModel() {

    private val _datas = MutableLiveData<ProfileData>()
    val datas : LiveData<ProfileData>
        get() = _datas

    fun loadData(){
        val temp = ProfileData("https://drive.google.com/uc?id=1gtrQXUFKsrkexSwWA9eN3Mh2Xtau3S3p", "Farriza Ahmad")
        _datas.postValue(temp)
    }
}