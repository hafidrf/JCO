package com.jcodonuts.app.ui.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.utils.recycler_adapter.RecyclerItem
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
                    temp.add(Divider16(""))
                    temp.add(Divider16(""))
                    Log.d("setRecyclerViewItems", "UPDATE LIST "+temp.size.toString())
                    _datas.postValue(temp)
                },{

                }
                )
    }

//    override fun onDonutMinusClick(donut: ProductDetailDonut) {
////        if(donut.quantity>0){
////            donut.quantity--
////            val temp = _datas.value?.toMutableList() ?: mutableListOf()
////            val pos = temp.indexOf(donut)
////            pos?.let { temp.set(it, donut) }
////            _datas.postValue(_datas.value)
////        }
//    }
//
//    override fun onDonutPlusClick(donut: ProductDetailDonut) {
////        val content = (_datas.value?.get(0)?.data as ProductDetailContent)
////        val maxDonut = content.quantity*12
////        val qtyInPcs = content.quantityInPcs
////        if(qtyInPcs<maxDonut){
//            donut.quantity++
//            val temp = _datas.value?.toMutableList() ?: mutableListOf()
//            val pos = temp.indexOf(donut)
//            pos?.let { temp.set(it, donut) }
//            _datas.postValue(temp)
////        }
//    }
//
//    override fun onMinusClick(content: ProductDetailContent) {
////        if(content.quantity>0){
////            content.quantity--
////            val temp = _datas.value?.toMutableList() ?: mutableListOf()
////            val pos = temp.indexOf(content)
////            pos?.let { temp.set(it, content) }
////            temp[pos] = content
////            temp.forEach {
////                if(it.data[0] is ProductDetailDonut){
////                    (it.data[0] as ProductDetailDonut).quantity=0
////                }
////            }
////            _datas.postValue(_datas.value)
////
////        }
//    }
//
//    override fun onPlusClick(content: ProductDetailContent) {
////        content.quantity++
////        val temp = _datas.value?.toMutableList() ?: mutableListOf()
////        val pos = temp.indexOf(content)
////        pos?.let { temp.set(it, content) }
////        _datas.postValue(_datas.value)
//    }

    override fun onPlusClick(position: Int) {

    }

    override fun onMinusClick(position: Int) {

    }

    override fun onDonutPlusClick(position: Int) {
        (_datas.value?.get(position) as ProductDetailDonut).quantity++

        _notifyItemUpdate.postValue(SingleEvents(position))
    }

    override fun onDonutMinusClick(position: Int) {
        if((_datas.value?.get(position) as ProductDetailDonut).quantity>0){
            (_datas.value?.get(position) as ProductDetailDonut).quantity--

            _notifyItemUpdate.postValue(SingleEvents(position))
        }
    }
}