package com.jcodonuts.app.ui.main.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel(),HomeControllerListener {
    private val TAG = "HomeViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()
    val menuSelected = MutableLiveData<Int>()

    private val _showDialogCannotOrder = MutableLiveData<SingleEvents<String>>()
    val showDialogCannotOrder : LiveData<SingleEvents<String>>
        get() = _showDialogCannotOrder

    private val _showLinkaja = MutableLiveData<SingleEvents<String>>()
    val showLinkaja : LiveData<SingleEvents<String>>
        get() = _showLinkaja

    private val _showQRcode = MutableLiveData<SingleEvents<String>>()
    val showQRcode : LiveData<SingleEvents<String>>
        get() = _showQRcode

    private val _showPickup = MutableLiveData<SingleEvents<String>>()
    val showPickup : LiveData<SingleEvents<String>>
        get() = _showPickup

    private val _showMenuSearch = MutableLiveData<SingleEvents<String>>()
    val showMenuSearch : LiveData<SingleEvents<String>>
        get() = _showMenuSearch

    private val _openProductDetail = MutableLiveData<SingleEvents<HomeMenuItem>>()
    val openProductDetail : LiveData<SingleEvents<HomeMenuItem>>
        get() = _openProductDetail

    private val _menus = mutableListOf<MenuCategory>()

    init {
        datas.value = mutableListOf()
    }

    @SuppressLint("CheckResult")
    fun loadPromo(){
        homeRepository.getPromo()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({ data ->
                val temp = datas.value?: mutableListOf()
                temp.add(HomeHeadSection("test"))
                temp.add(HomeSearchSection("test"))
                temp.add(HomePromoHeader("test"))
                temp.add(data)
                temp.add(HomeMenuHeader("What are you looking for?"))
                getMenus()
                temp.add(HomeMenuCategories(_menus))

                loadMenuItems()
            }, {

            })
    }

    @SuppressLint("CheckResult")
    fun loadMenuItems(){
        homeRepository.getMenuItems()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ data ->
                    val temp = datas.value?: mutableListOf()
                    temp.addAll(data.homeMenuItems)

                    datas.value=temp
                }, {

                })
    }

    private fun getMenus(){
        _menus.add(MenuCategory("All", R.drawable.img_jco_menu_all, true))
        _menus.add(MenuCategory("Promo", R.drawable.img_jco_menu_promo, false))
        _menus.add(MenuCategory("Donuts", R.drawable.img_jco_menu_donuts, false))
        _menus.add(MenuCategory("Beverage", R.drawable.img_jco_menu_beverage, false))
        _menus.add(MenuCategory("Cookies & Nuts", R.drawable.img_jco_menu_cookies, false))
        _menus.add(MenuCategory("Hampers", R.drawable.img_jco_menu_hampers, false))
        _menus.add(MenuCategory("Others", R.drawable.img_jco_menu_other, false))
    }

    override fun onLinkajaClick() {
        _showLinkaja.value = SingleEvents("linkaja")
    }

    override fun onQrCodeClick() {
        _showQRcode.value = SingleEvents("qrcode")
    }

    override fun onPickupClick() {
        _showPickup.value = SingleEvents("show_pickup")
    }

    override fun onSearchClick() {
        _showMenuSearch.value = SingleEvents("menu_search")
    }

    override fun onBannerPromoClick(promoBanner: PromoBanner) {

    }

    override fun onPromoSeeAllClick() {

    }

    override fun onMenuCategoryClick(menuCategory: MenuCategory) {
        if(menuCategory.img!=menuSelected.value){
            menuSelected.postValue(menuCategory.img)
        }
    }

    override fun onMenuItemClick(index: Int) {
        datas.value?.let {
            _openProductDetail.value = SingleEvents(it[index] as HomeMenuItem)
        }
    }
}