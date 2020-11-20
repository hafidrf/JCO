package com.jcodonuts.app.ui.mainHome

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel(),HomeControllerListener {
    private val TAG = "HomeViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()
    val menuSelected = MutableLiveData<Int>()

    private val _menus = mutableListOf<Menu>()
    private val _menuItems = mutableListOf<HomeMenuItem>()

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

                getMenuItems()
                temp.add(HomeMenuItems(_menuItems))

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

    private fun getMenuItems(){
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/fife/ABSRlIrWo4KsPoFQyRziTNzi5ixTvaBUZvOr9Yj05G6Wy5XkuUoETa8egDaDeEKsK5h3fmPM6Wzd6v2wP7QSLV7kv3cNQaQF0djuO6v8KrezBC50bU7APxoizJhwfHIm4inDBAJwfQNUzIk4_PSVSIPzXDvzgptZCIQqy6rgPILGDVNKdcuZQrXy1FkAkWfbj6bUv4Em_hRPvhlPVkzyJHVG7ufrB4pIJuwHme7xGrCHHEqYf0xycMeg0Iu_5QVgAdbFyb8TRQ49g5bSmSDnLB1yiPkUZZ01iXwneVOdIYbeh1mNT75NGwCb0QRplVmEkbeh2j862J4AglzPPAPBfK7LGNzRKZ95OSvw5KLlZv7FJWmwZ6roLAngkMBxnG-KWTS11QGa8TG7n5b6UCANgMPwQPbcZwC6J_qb7ZaxouTNFDz6UrZbc3ffFtnkI9x1OP5Cbhqu4RceqW_EmTqERqUCTUBO3d16mh-RhyORRYtIOPUfiwwBifePmrzWN46tKDrVEbeKs5caO06MDK8duL1LooI-U-jJT4Q43GWh6g34XxpED81s6BrV_5EsiRVuhoTJfKM0c0FV_8ryyY4StDRTT982a_rGKl0nZvGTdS4qqVJVNZVPvK6pS_PYtOkhL6P7jxbPKGCHYCrRu5RCttUUA9v6_wahyuURtXv-l0Zuz6VH7Iu3wmKXRssAu86QscUfhNZaw7-s-ZYO0oeM7kTiqgQv_Hj6W2CxWzI=w1920-h399-ft", "Rp. 5,000", true, true))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/fife/ABSRlIrWo4KsPoFQyRziTNzi5ixTvaBUZvOr9Yj05G6Wy5XkuUoETa8egDaDeEKsK5h3fmPM6Wzd6v2wP7QSLV7kv3cNQaQF0djuO6v8KrezBC50bU7APxoizJhwfHIm4inDBAJwfQNUzIk4_PSVSIPzXDvzgptZCIQqy6rgPILGDVNKdcuZQrXy1FkAkWfbj6bUv4Em_hRPvhlPVkzyJHVG7ufrB4pIJuwHme7xGrCHHEqYf0xycMeg0Iu_5QVgAdbFyb8TRQ49g5bSmSDnLB1yiPkUZZ01iXwneVOdIYbeh1mNT75NGwCb0QRplVmEkbeh2j862J4AglzPPAPBfK7LGNzRKZ95OSvw5KLlZv7FJWmwZ6roLAngkMBxnG-KWTS11QGa8TG7n5b6UCANgMPwQPbcZwC6J_qb7ZaxouTNFDz6UrZbc3ffFtnkI9x1OP5Cbhqu4RceqW_EmTqERqUCTUBO3d16mh-RhyORRYtIOPUfiwwBifePmrzWN46tKDrVEbeKs5caO06MDK8duL1LooI-U-jJT4Q43GWh6g34XxpED81s6BrV_5EsiRVuhoTJfKM0c0FV_8ryyY4StDRTT982a_rGKl0nZvGTdS4qqVJVNZVPvK6pS_PYtOkhL6P7jxbPKGCHYCrRu5RCttUUA9v6_wahyuURtXv-l0Zuz6VH7Iu3wmKXRssAu86QscUfhNZaw7-s-ZYO0oeM7kTiqgQv_Hj6W2CxWzI=w1920-h399-ft", "Rp. 5,000", true, true))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/fife/ABSRlIrWo4KsPoFQyRziTNzi5ixTvaBUZvOr9Yj05G6Wy5XkuUoETa8egDaDeEKsK5h3fmPM6Wzd6v2wP7QSLV7kv3cNQaQF0djuO6v8KrezBC50bU7APxoizJhwfHIm4inDBAJwfQNUzIk4_PSVSIPzXDvzgptZCIQqy6rgPILGDVNKdcuZQrXy1FkAkWfbj6bUv4Em_hRPvhlPVkzyJHVG7ufrB4pIJuwHme7xGrCHHEqYf0xycMeg0Iu_5QVgAdbFyb8TRQ49g5bSmSDnLB1yiPkUZZ01iXwneVOdIYbeh1mNT75NGwCb0QRplVmEkbeh2j862J4AglzPPAPBfK7LGNzRKZ95OSvw5KLlZv7FJWmwZ6roLAngkMBxnG-KWTS11QGa8TG7n5b6UCANgMPwQPbcZwC6J_qb7ZaxouTNFDz6UrZbc3ffFtnkI9x1OP5Cbhqu4RceqW_EmTqERqUCTUBO3d16mh-RhyORRYtIOPUfiwwBifePmrzWN46tKDrVEbeKs5caO06MDK8duL1LooI-U-jJT4Q43GWh6g34XxpED81s6BrV_5EsiRVuhoTJfKM0c0FV_8ryyY4StDRTT982a_rGKl0nZvGTdS4qqVJVNZVPvK6pS_PYtOkhL6P7jxbPKGCHYCrRu5RCttUUA9v6_wahyuURtXv-l0Zuz6VH7Iu3wmKXRssAu86QscUfhNZaw7-s-ZYO0oeM7kTiqgQv_Hj6W2CxWzI=w1920-h399-ft", "Rp. 5,000", true, true))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/fife/ABSRlIrWo4KsPoFQyRziTNzi5ixTvaBUZvOr9Yj05G6Wy5XkuUoETa8egDaDeEKsK5h3fmPM6Wzd6v2wP7QSLV7kv3cNQaQF0djuO6v8KrezBC50bU7APxoizJhwfHIm4inDBAJwfQNUzIk4_PSVSIPzXDvzgptZCIQqy6rgPILGDVNKdcuZQrXy1FkAkWfbj6bUv4Em_hRPvhlPVkzyJHVG7ufrB4pIJuwHme7xGrCHHEqYf0xycMeg0Iu_5QVgAdbFyb8TRQ49g5bSmSDnLB1yiPkUZZ01iXwneVOdIYbeh1mNT75NGwCb0QRplVmEkbeh2j862J4AglzPPAPBfK7LGNzRKZ95OSvw5KLlZv7FJWmwZ6roLAngkMBxnG-KWTS11QGa8TG7n5b6UCANgMPwQPbcZwC6J_qb7ZaxouTNFDz6UrZbc3ffFtnkI9x1OP5Cbhqu4RceqW_EmTqERqUCTUBO3d16mh-RhyORRYtIOPUfiwwBifePmrzWN46tKDrVEbeKs5caO06MDK8duL1LooI-U-jJT4Q43GWh6g34XxpED81s6BrV_5EsiRVuhoTJfKM0c0FV_8ryyY4StDRTT982a_rGKl0nZvGTdS4qqVJVNZVPvK6pS_PYtOkhL6P7jxbPKGCHYCrRu5RCttUUA9v6_wahyuURtXv-l0Zuz6VH7Iu3wmKXRssAu86QscUfhNZaw7-s-ZYO0oeM7kTiqgQv_Hj6W2CxWzI=w1920-h399-ft", "Rp. 5,000", false, false))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, false))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, false))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, false))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, false))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, true))
        _menuItems.add(HomeMenuItem("Donuts","https://lh3.googleusercontent.com/u/0/d/1-o6w4zl7xXAMxOwkIvg-41dr3eh9dEBP=w400-h380-p-k-nu-iv1", "Rp. 5,000", false, true))
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
        Log.d(TAG, "$menuItem")

    }
}