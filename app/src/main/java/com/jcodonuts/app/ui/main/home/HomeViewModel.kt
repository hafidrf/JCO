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

    private val _menus = mutableListOf<Menu>()

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
                temp.add(HomeMenus(_menus))

                datas.postValue(temp)
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
                    temp.add(data)

                    datas.postValue(temp)
                }, {

                })
    }

    private fun getMenus(){
        _menus.add(Menu("All", R.drawable.img_jco_menu_all, true))
        _menus.add(Menu("Promo", R.drawable.img_jco_menu_promo, false))
        _menus.add(Menu("Donuts", R.drawable.img_jco_menu_donuts, false))
        _menus.add(Menu("Beverage", R.drawable.img_jco_menu_beverage, false))
        _menus.add(Menu("Cookies & Nuts", R.drawable.img_jco_menu_cookies, false))
        _menus.add(Menu("Hampers", R.drawable.img_jco_menu_hampers, false))
        _menus.add(Menu("Others", R.drawable.img_jco_menu_other, false))
    }

    override fun onLinkajaClick() {
        _showLinkaja.value = SingleEvents("linkaja")
    }

    override fun onSearchClick() {

    }

    override fun onBannerPromoClick(promoBanner: PromoBanner) {

    }

    override fun onPromoSeeAllClick() {

    }

    override fun onMenuClick(menu: Menu) {
        if(menu.img!=menuSelected.value){
            menuSelected.postValue(menu.img)
        }
    }

    override fun onMenuItemClick(menuItem: HomeMenuItem) {
        _showDialogCannotOrder.value = SingleEvents(menuItem.toString())
    }
}