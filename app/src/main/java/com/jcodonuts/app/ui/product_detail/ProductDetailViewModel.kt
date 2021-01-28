package com.jcodonuts.app.ui.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.remote.model.req.ProductDetailReq
import com.jcodonuts.app.data.remote.model.req.ProductFavoriteReq
import com.jcodonuts.app.data.remote.model.res.ProductDetail
import com.jcodonuts.app.data.repository.HomeRepository
import com.jcodonuts.app.data.repository.ProductRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.Converter
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SharedPreference
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
        private val productRepository: ProductRepository,
        private val homeRepository: HomeRepository,
        private val schedulers: SchedulerProvider
): BaseViewModel(), ProductDetailControllerListener {

    private val _datas = MutableLiveData<MutableList<BaseCell>>()
    val datas : LiveData<MutableList<BaseCell>>
        get() = _datas

    private val _productDetail = MutableLiveData<ProductDetail>()
    val productDetail : LiveData<ProductDetail>
        get() = _productDetail

    private val _addToCart = MutableLiveData<SingleEvents<String>>()
    val addToCart : LiveData<SingleEvents<String>>
        get() = _addToCart

    private lateinit var menuCode:String

    @SuppressLint("CheckResult")
    fun loadDetail(id:String){
        menuCode = id

        //set loading page
        val loading = mutableListOf<BaseCell>()
        loading.add(LoadingPage())
        _datas.postValue(loading)


        val body = ProductDetailReq("1","Jakarta Barat","1",Converter.md5(id))
        lastDisposable = productRepository.getProductDetail(body)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe ({ model ->
                    _productDetail.value = model.data

                    Log.d("DATA__", "is favorite "+model.data.is_favorite)

                    val temp = mutableListOf<BaseCell>()
                    temp.add(ProductDetailContent(
                        "#FAC1A8",
                        "#F25A17",
                        model.data.menu_desc,
                        "",
                        model.data.menu_price,
                        Converter.rupiah(model.data.menu_price),
                        model.data.menu_name,
                        1,
                        0,
                        12,
                        model.data.category_name,
                    ))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    _datas.postValue(temp)
                },{
                    handleError(it)
                })



//                    {
//                    data ->
//                    val temp = _datas.value?.toMutableList() ?: mutableListOf()
//                    temp.add(data.content)
//                    temp.addAll(data.donuts)

//                    _datas.postValue(temp)
//                },{
//
//                }
//                )

        lastDisposable?.let { compositeDisposable.add(it) }
    }

    override fun onPlusClick(position: Int) {
        _datas.value?.let {
            val content = (it[position] as ProductDetailContent).copy()
            content.quantity++
            it[position] = content
            _datas.postValue(it)
        }
    }

    override fun onMinusClick(position: Int) {
        _datas.value?.let {
            val content = (it[position] as ProductDetailContent).copy()
            if(content.quantity>0){
                content.quantity--

                it[position] = content
                _datas.postValue(it)
            }
        }
    }

    override fun onDonutPlusClick(position: Int) {
        _datas.value?.let {
            val header = (it[0] as ProductDetailContent).copy()
            val max = header.quantity*header.totalPerPack
            if(header.quantityInPcs<max){
                val donut = (it[position] as ProductDetailDonut).copy()
                    donut.quantity++

                header.quantityInPcs++

                it[0] = header
                it[position] = donut
                _datas.postValue(it)
            }
        }
    }

    override fun onDonutMinusClick(position: Int) {
        _datas.value?.let {
            if((it[position] as ProductDetailDonut).quantity>0){

                val donut = (it[position] as ProductDetailDonut).copy()
                    donut.quantity--

                val header = (it[0] as ProductDetailContent).copy()
                    header.quantityInPcs--

                it[0] = header
                it[position] = donut
                _datas.postValue(it)
            }
        }
    }

    override fun onAddToCart() {
        _addToCart.value = SingleEvents("addToCart")
    }

    override fun onBtnFavoriteClick() {
        productDetail.value?.let{modelData->
            val favorite = if(modelData.is_favorite == "1") "0" else "1"
            val body = ProductFavoriteReq(favorite,"1", menuCode)
            lastDisposable = homeRepository.setProductFavorite(body)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    modelData.is_favorite = favorite
                    _productDetail.postValue(modelData)
                },{
                    handleError(it)
                })

            lastDisposable?.let { compositeDisposable.add(it) }
        }
    }
}