package com.jcodonuts.app.ui.auth.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcodonuts.app.R
import com.jcodonuts.app.data.remote.model.req.LoginReq
import com.jcodonuts.app.data.repository.AuthRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SharedPreference
import com.jcodonuts.app.utils.SingleEvents
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val schedulers: SchedulerProvider,
    private val sharedPreference: SharedPreference,
    private val app:Application
): BaseViewModel() {

    private val _errorPhone = MutableLiveData<SingleEvents<String>>()
    val errorPhone : LiveData<SingleEvents<String>>
        get() = _errorPhone

    private val _errorPassword = MutableLiveData<SingleEvents<String>>()
    val errorPassword : LiveData<SingleEvents<String>>
        get() = _errorPassword

    private val _loginFailed = MutableLiveData<SingleEvents<String>>()
    val loginFailed : LiveData<SingleEvents<String>>
        get() = _loginFailed

    private val _closeLoginPage = MutableLiveData<SingleEvents<String>>()
    val closeLoginPage : LiveData<SingleEvents<String>>
        get() = _closeLoginPage

    private val _loggingIn = MutableLiveData<Boolean>()
    val loggingIn : LiveData<Boolean>
        get() = _loggingIn


    fun login(phone:String, password:String){
        when {
            phone.isEmpty() -> {
                _errorPhone.value = SingleEvents(app.getString(R.string.err_phone_empty))
            }
            password.isEmpty() -> {
                _errorPassword.value = SingleEvents(app.getString(R.string.err_password_empty))
            }
            password.length<5 -> {
                _errorPassword.value = SingleEvents(app.getString(R.string.err_password_length))
            }
            else -> {
                _loggingIn.postValue(true)
                val body = LoginReq(phone, password)
                lastDisposable = authRepository.login(body)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({ model ->
                        _loggingIn.postValue(false)
                        if (model.status_code == 200) {
                            sharedPreference.save(SharedPreference.ACCESS_TOKEN, model.accessToken)
                            sharedPreference.save(
                                SharedPreference.REFRESH_TOKEN,
                                model.refreshToken
                            )
                            sharedPreference.save(SharedPreference.FROM_LOGIN, true)

                            _closeLoginPage.value = SingleEvents("close")
                        } else {
                            _loginFailed.value = SingleEvents(model.error)
                        }
                    }, { e ->
                        _loggingIn.postValue(false)
                        _loginFailed.value = SingleEvents(getErrorMsg(e))
                    })

                lastDisposable?.let { compositeDisposable.add(it) }
            }
        }
    }
}