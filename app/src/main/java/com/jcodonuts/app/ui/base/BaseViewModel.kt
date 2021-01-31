package com.jcodonuts.app.ui.base

import android.util.Log
import androidx.lifecycle.*
import com.jcodonuts.app.data.local.LogoutEvent
import com.jcodonuts.app.data.remote.helper.Failure
import com.jcodonuts.app.data.remote.helper.NetworkState
import com.jcodonuts.app.data.remote.helper.StatusCode
import com.jcodonuts.app.utils.EspressoIdlingResource
import com.jcodonuts.app.utils.SingleEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus





abstract class BaseViewModel : ViewModel() {
    private val TAG = "BaseViewModel"

    protected var lastDisposable: Disposable? = null

    private val _networkState = MutableLiveData<NetworkState>()

    val networkState: LiveData<NetworkState> get() = _networkState

    val compositeDisposable = CompositeDisposable()

    private val _onRequestFailure = MutableLiveData<SingleEvents<String>>()
    val onRequestFailure : LiveData<SingleEvents<String>>
        get() = _onRequestFailure

    protected fun <T> composeObservable(task: () -> Observable<T>): Observable<T> = task()
        .doOnSubscribe { EspressoIdlingResource.increment() } // App is busy until further notice
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
        }

    /**
     * unsubscribe last subscription rxJava
     */
    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * clear all subscription and dispose
     */
    private fun dispose() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    /**
     * handle error request on throwable as Failure
     * @param throwable wrapp with failure
     */
    fun handleError(throwable: Throwable?) {
        lateinit var error:NetworkState
        if(throwable is Failure){
            error = NetworkState.error(throwable)
            if(throwable.code == 520){
                EventBus.getDefault().post(LogoutEvent("Session Expired"))
            }
        } else {
            error = NetworkState.error(Failure(StatusCode.UNKNOWN_ERROR, "There is unknown error"))
        }
        _networkState.postValue(error)
    }

    fun getErrorMsg(throwable: Throwable?):String {
        return if(throwable is Failure){
            throwable.msg
        } else {
            "There is unknown error"
        }
    }

    fun networkState(state: NetworkState){
        _networkState.postValue(state)
    }

    /**
     * dispose all subscription when lifecycle onDestory
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}
