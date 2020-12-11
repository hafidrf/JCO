package com.jcodonuts.app.ui.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.*
import com.jcodonuts.app.data.repository.ProductRepository
import com.jcodonuts.app.ui.base.BaseModel
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
        private val productRepository: ProductRepository,
        private val scheduler: SchedulerProvider
): BaseViewModel(), ViewHolderListener {

    private val _datas = MutableLiveData<MutableList<BaseModel>>()
    val datas : LiveData<MutableList<BaseModel>>
        get() = _datas

    private val _notifyContentUpdate = MutableLiveData<SingleEvents<Int>>()
    val notifyContentUpdate : LiveData<SingleEvents<Int>>
        get() = _notifyContentUpdate

    private val _notifyItemUpdate = MutableLiveData<SingleEvents<Int>>()
    val notifyItemUpdate : LiveData<SingleEvents<Int>>
        get() = _notifyItemUpdate

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
                    _datas.postValue(temp)
                },{

                }
                )
    }

    override fun onPlusClick(position: Int) {
        _datas.value?.let {
            val content = (it[position] as ProductDetailContent)
            content.quantity++
            _notifyContentUpdate.postValue(SingleEvents(position))
        }
    }

    override fun onMinusClick(position: Int) {
        _datas.value?.let {
            val content = (it[position] as ProductDetailContent)
            if(content.quantity>0){
                content.quantity--
                _notifyContentUpdate.postValue(SingleEvents(position))
            }
        }
    }

    override fun onDonutPlusClick(position: Int) {
        _datas.value?.let {
            val content = (it[0] as ProductDetailContent)
            val max = content.quantity*content.totalPerPack
            if(content.quantityInPcs<max){
                (it[position] as ProductDetailDonut).quantity++
                _notifyItemUpdate.postValue(SingleEvents(position))

                content.quantityInPcs++
                _notifyContentUpdate.postValue(SingleEvents(0))
            }
        }
    }

    override fun onDonutMinusClick(position: Int) {
        _datas.value?.let {
            if((it[position] as ProductDetailDonut).quantity>0){

                (it[position] as ProductDetailDonut).quantity--
                _notifyItemUpdate.postValue(SingleEvents(position))

                (it[0] as ProductDetailContent).quantityInPcs--
                _notifyContentUpdate.postValue(SingleEvents(0))
            }
        }
    }
}