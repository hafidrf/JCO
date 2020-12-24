package com.jcodonuts.app.ui.main.menu_search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.data.repository.PickupRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.ui.main.home.HomeControllerListener
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class MenuSearchViewModel @Inject constructor(
        private val homeRepository: HomeRepository,
        private val schedulers: SchedulerProvider
): BaseViewModel(), HomeControllerListener {
    private val TAG = "PickupViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadLocations(){
        homeRepository.getMenuItems()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ data ->
                    datas.value?.let {
                        it.add(CommonSearch("temp"))
                        it.add(MenuSearchTagName(listOf("Promo", "Donuts", "Beverage", "Cookies & Nuts", "Hampers", "Others")))
                        it.addAll(data.homeMenuItems)
                        datas.postValue(it)
                    }
                }, {

                })
    }

    fun SearchLocations(query:String){
        Log.d(TAG, query)
    }

    override fun onLinkajaClick() {

    }

    override fun onQrCodeClick() {

    }

    override fun onPickupClick() {

    }

    override fun onSearchClick() {

    }

    override fun onSwitchAppClick() {

    }

    override fun onBannerPromoClick(promoBanner: PromoBanner) {

    }

    override fun onPromoSeeAllClick() {

    }

    override fun onMenuCategoryClick(menuCategory: MenuCategory) {

    }

    override fun onMenuItemClick(index: Int) {

    }

    override fun onMenuItemFavoriteClick(index: Int) {

    }
}