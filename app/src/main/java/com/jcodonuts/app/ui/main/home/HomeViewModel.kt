package com.jcodonuts.app.ui.main.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.remote.model.req.HomeReq
import com.jcodonuts.app.data.remote.model.res.HomeRes
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SharedPreference
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val schedulers: SchedulerProvider,
    private val sharedPreference: SharedPreference,
    private val app:Application,
    private val gson:Gson
): BaseViewModel(),HomeControllerListener {
    private val TAG = "HomeViewModel"

    val datas = MutableLiveData<MutableList<BaseCell>>()
    val menuSelected = MutableLiveData<String>()

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

    private val _showChangeApp = MutableLiveData<SingleEvents<String>>()
    val showChangeApp : LiveData<SingleEvents<String>>
        get() = _showChangeApp

    private val _showHotPromo = MutableLiveData<SingleEvents<String>>()
    val showHotPromo : LiveData<SingleEvents<String>>
        get() = _showHotPromo

    private val _openProductDetail = MutableLiveData<SingleEvents<HomeMenuItem>>()
    val openProductDetail : LiveData<SingleEvents<HomeMenuItem>>
        get() = _openProductDetail

    private val _menus = mutableListOf<MenuCategory>()

    init {
        datas.value = mutableListOf()
    }

    fun checkFromLogin(){
        if(sharedPreference.getValueBoolean(SharedPreference.FROM_LOGIN, false)){
            fetchHome()
            sharedPreference.save(SharedPreference.FROM_LOGIN, false)
        }
    }

    fun fetchHome(){
        val data = sharedPreference.getValueString(SharedPreference.DATA_HOME)
        if(data!=null){
            val model = gson.fromJson(data, HomeRes::class.java)
            parseFetchHome(model)
        }else{

            //add loading page
            val loading = mutableListOf<BaseCell>()
            loading.add(LoadingPage())
            datas.value=loading

            fetchHomeFromAPI()
        }
    }

    fun fetchHomeFromAPI(){
        val body = HomeReq( "Jakarta Barat", "20")
        lastDisposable = homeRepository.fetchHome(body)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({ model ->
                parseFetchHome(model)

                //save json to sharepreference
                val data = gson.toJson(model)
                sharedPreference.save(SharedPreference.DATA_HOME, data)
            },{
                handleError(it)
            })

        lastDisposable?.let { compositeDisposable.add(it) }
    }

    private fun parseFetchHome(model:HomeRes){
        val temp = mutableListOf<BaseCell>()

        if(isLoggedIn()){
            temp.add(HomeHeadSection(model.user.member_name, model.user.member_point))
        }else{
            temp.add(Divider16())
            temp.add(Divider16())
            temp.add(Divider16())
        }

        temp.add(HomeSearchSection("test"))
        temp.add(HomePromoHeader("test"))
        val promos = mutableListOf<PromoBanner>()
        model.promos.map {
            promos.add(PromoBanner(it.menu_image))
        }
        temp.add(HomePromos(promos))

        temp.add(HomeMenuHeader(app.getString(R.string.what_are_you_looking_for)))

        val menus = mutableListOf<MenuCategory>()
        model.category.map {
            if(it.category_name == "all"){
                menus.add(0,MenuCategory(it.category_title, it.category_img,
                    true
                ))
                menuSelected.postValue(it.category_img)
            }else{
                menus.add(MenuCategory(it.category_title, it.category_img,
                    false
                ))
            }

        }

        temp.add(HomeMenuCategories(menus))

        model.products.map {
            temp.add(HomeMenuItem(it.menu_name, it.menu_image, it.menu_price, false, it.is_promo=="1", it.is_freedelivery=="1", it.is_favorite=="1"))
        }

        datas.value=temp
    }

    override fun onLinkajaClick() {
        _showLinkaja.value = SingleEvents("linkaja")
    }

    override fun onQrCodeClick() {
        _showQRcode.value = SingleEvents("qrcode")
    }

    override fun onPickupClick() {
        if(isLoggedIn()){
            _showPickup.value = SingleEvents("show_pickup")
        }else{
            showDlgCannotOrder()
        }
    }

    override fun onSearchClick() {
        if(isLoggedIn()){
            _showMenuSearch.value = SingleEvents("menu_search")
        }else{
            showDlgCannotOrder()
        }
    }

    override fun onSwitchAppClick() {
        _showChangeApp.value = SingleEvents("change_app")
    }

    override fun onBannerPromoClick(promoBanner: PromoBanner) {

    }

    override fun onPromoSeeAllClick() {
        if(isLoggedIn()){
            _showHotPromo.value = SingleEvents("hot_promo")
        }else{
            showDlgCannotOrder()
        }
    }

    override fun onMenuCategoryClick(menuCategory: MenuCategory) {
        if(menuCategory.img!=menuSelected.value){
            menuSelected.postValue(menuCategory.img)
        }
    }

    override fun onMenuItemClick(index: Int) {
        if(isLoggedIn()){
            datas.value?.let {
                _openProductDetail.value = SingleEvents(it[index] as HomeMenuItem)
            }
        }else{
            showDlgCannotOrder()
        }
    }

    override fun onMenuItemFavoriteClick(index: Int) {
        if(isLoggedIn()){
            datas.value?.let {
                val temp = (it[index] as HomeMenuItem).copy()
                temp.isFavorite = !temp.isFavorite
                it[index] = temp
                datas.postValue(it)
            }
        }else{
            showDlgCannotOrder()
        }
    }

    private fun showDlgCannotOrder(){
        _showDialogCannotOrder.value = SingleEvents("_showDialogCannotOrder")
    }

    private fun isLoggedIn():Boolean{
        val data = sharedPreference.getValueString(SharedPreference.ACCESS_TOKEN)
        return data != null && data != ""
    }
}