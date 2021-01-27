package com.jcodonuts.app.ui.product_detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.BaseCell
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.data.local.ProductDetailContent
import com.jcodonuts.app.data.local.ProductDetailDonut
import com.jcodonuts.app.data.repository.ProductRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
        private val productRepository: ProductRepository,
        private val scheduler: SchedulerProvider
): BaseViewModel(), ProductDetailControllerListener {

    private val _datas = MutableLiveData<MutableList<BaseCell>>()
    val datas : LiveData<MutableList<BaseCell>>
        get() = _datas

    private val _addToCart = MutableLiveData<SingleEvents<String>>()
    val addToCart : LiveData<SingleEvents<String>>
        get() = _addToCart

    @SuppressLint("CheckResult")
    fun loadDetail(){
        productRepository.getDetail()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe ( {
                    data ->
                    val temp = _datas.value?.toMutableList() ?: mutableListOf()
                    temp.add(data.content)
                    temp.addAll(data.donuts)
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    _datas.postValue(temp)
                },{

                }
                )
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

    }
}