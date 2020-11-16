package com.jcodonuts.app.ui.article

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.data.local.Country
import com.jcodonuts.app.data.remote.helper.DataStatus
import com.jcodonuts.app.data.remote.helper.NetworkState
import com.jcodonuts.app.data.repository.MenuRepository
import com.jcodonuts.app.data.repository.NewsRepository
import com.jcodonuts.app.ui.base.BaseModel
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.ui.base.ItemLoading
import com.jcodonuts.app.utils.SchedulerProvider
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val menuRepository: MenuRepository,
    private val newsRepository: NewsRepository,
    private val schedulers: SchedulerProvider
): BaseViewModel() {

    private val TAG = "MainViewModel"

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _article = MutableLiveData<List<BaseModel>>()
    val article: LiveData<List<BaseModel>> get() = _article

    private val _dataStatus = MutableLiveData<DataStatus>()
    val dataStatus: LiveData<DataStatus> get() = _dataStatus

    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country> get() = _country

    private var page:Int=1
    private var totalResults = 0

    fun loadMenu(context: Context){
        menuRepository.getMenus(context)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({ countries ->
                _countries.postValue(countries)
            },{
                Log.d(TAG, ": $it");
            })
    }

    fun loadNews(country:Country){
        _country.value = country
        retry()
    }

    fun retry(){
        networkState(NetworkState.LOADING)
        page=1
        _article.postValue(mutableListOf())
        getNews()
    }

    fun loadMore(){
        page++
        getNews()
    }

    private fun getNews(){
        country.value?.let { it ->
            lastDisposable = newsRepository.getNews(it.code , page.toString())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({ models ->
                    totalResults = models.totalResults
                    if(models.totalResults>0){

                        val temp = _article.value?.toMutableList() ?: mutableListOf()
                        temp.removeAll { it is ItemLoading }

                        temp.addAll(models.articles)

                        if(totalResults>temp.size){
                            temp.add(ItemLoading(""))
                        }

                        _article.postValue(temp)

                        _dataStatus.postValue(DataStatus.NOT_EMPTY)
                    }else{
                        _dataStatus.postValue(DataStatus.EMPTY)
                    }
                    networkState(NetworkState.LOADED)
                },{
                    handleError(it)
                })

            lastDisposable?.let { compositeDisposable.add(it) }
        }
    }

}