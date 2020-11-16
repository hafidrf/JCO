package com.jcodonuts.app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.EspressoIdlingResource
import com.jcodonuts.app.utils.SingleEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val _complete = MutableLiveData<SingleEvents<Boolean>>()

    val complete : LiveData<SingleEvents<Boolean>>
        get() = _complete

    lateinit var disposable:Disposable

    fun countSplash(){
        disposable = Observable.timer(3, TimeUnit.SECONDS)
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if(!EspressoIdlingResource.getIdlingResource().isIdleNow){
                    EspressoIdlingResource.decrement()
                }
            }
            .subscribe{
                _complete.value = SingleEvents(true)
            }
    }

    fun dispose(){
        disposable.dispose()
    }
}