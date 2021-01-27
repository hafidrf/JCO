package com.jcodonuts.app.ui.main.wishlist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.MenuCategory
import com.jcodonuts.app.data.local.PromoBanner
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.ui.main.home.HomeControllerListener
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class WishlistViewModel @Inject constructor(
        private val homeRepository: HomeRepository,
        private val schedulers: SchedulerProvider
): HomeControllerListener,  BaseViewModel() {

    val datas = MutableLiveData<MutableList<BaseCell>>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadLocations(){
        homeRepository.getMenuItemsWishlist()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ data ->
                    datas.value?.let {
                        it.addAll(data.homeMenuItems)
                        datas.postValue(it)
                    }
                }, {

                })
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
        datas.value?.let{
            it.removeAt(index)
            datas.postValue(it)
        }
    }


}