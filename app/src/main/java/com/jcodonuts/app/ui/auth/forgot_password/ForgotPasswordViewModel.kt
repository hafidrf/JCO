package com.jcodonuts.app.ui.auth.forgot_password

import android.app.Activity
import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.jcodonuts.app.R
import com.jcodonuts.app.data.remote.model.req.ChangePsswordReq
import com.jcodonuts.app.data.remote.model.req.RegisterReq
import com.jcodonuts.app.data.repository.AuthRepository
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.NetworkUtils
import com.jcodonuts.app.utils.SchedulerProvider
import com.jcodonuts.app.utils.SharedPreference
import com.jcodonuts.app.utils.SingleEvents
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val schedulers: SchedulerProvider,
    private val app: Application
): BaseViewModel() {

    private val _errorPhone = MutableLiveData<SingleEvents<String>>()
    val errorPhone : LiveData<SingleEvents<String>>
        get() = _errorPhone

    private val _errorPassword = MutableLiveData<SingleEvents<String>>()
    val errorPassword : LiveData<SingleEvents<String>>
        get() = _errorPassword

    private val _errorConfirmPassword = MutableLiveData<SingleEvents<String>>()
    val errorConfirmPassword : LiveData<SingleEvents<String>>
        get() = _errorConfirmPassword

    private val _showVerificationPage = MutableLiveData<SingleEvents<String>>()
    val showVerificationPage : LiveData<SingleEvents<String>>
        get() = _showVerificationPage

    private val _showResetFormPage = MutableLiveData<SingleEvents<String>>()
    val showResetFormPage : LiveData<SingleEvents<String>>
        get() = _showResetFormPage

    private val _showToast = MutableLiveData<SingleEvents<String>>()
    val showToast : LiveData<SingleEvents<String>>
        get() = _showToast

    private val _doResendOTP = MutableLiveData<SingleEvents<String>>()
    val doResendOTP : LiveData<SingleEvents<String>>
        get() = _doResendOTP

    private val _countDown = MutableLiveData<String>()
    val countDown : LiveData<String>
        get() = _countDown

    private val _enableResend = MutableLiveData<Boolean>()
    val enableResend : LiveData<Boolean>
        get() = _enableResend

    private val _showLoadingOTP = MutableLiveData<Boolean>()
    val showLoadingOTP : LiveData<Boolean>
        get() = _showLoadingOTP

    private val _closeResetPage = MutableLiveData<SingleEvents<String>>()
    val closeResetPage : LiveData<SingleEvents<String>>
        get() = _closeResetPage

    private val _resetFailed = MutableLiveData<SingleEvents<String>>()
    val resetFailed : LiveData<SingleEvents<String>>
        get() = _resetFailed

    private var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var countDownTimer: CountDownTimer
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var activity: Activity
    private var storedVerificationId: String? = ""

    init {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                _showLoadingOTP.value = false
            }

            override fun onVerificationFailed(e: FirebaseException) {
                _showLoadingOTP.value = false
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                storedVerificationId = verificationId
                _showToast.value = SingleEvents(app.getString(R.string.verification_code_sent))

                _showLoadingOTP.value = false
                _showVerificationPage.value = SingleEvents("show")
                startCountDown()
            }
        }

        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(millisUntilFinished / 1000<10){
                    _countDown.value = "0:0"+millisUntilFinished / 1000
                }else{
                    _countDown.value = "0:"+millisUntilFinished / 1000
                }
            }

            override fun onFinish() {
                _countDown.value = "0:00"
                _enableResend.value = true
            }
        }
    }

    fun resendOTP(){
        _doResendOTP.value = SingleEvents("resend")
        startCountDown()
    }

    fun startCountDown(){
        _enableResend.value = false
        countDownTimer.start()
    }

    fun stopCountDown(){
        _enableResend.value = true
        countDownTimer.cancel()
    }

    fun requestOTP(phone: String, auth: FirebaseAuth, activity: Activity){
        firebaseAuth = auth
        this.activity = activity

        when{
            phone.isEmpty() -> {
                _errorPhone.value = SingleEvents(app.getString(R.string.err_phone_empty))
            }
            phone.length<10 ->{
                _errorPhone.value = SingleEvents(app.getString(R.string.err_phone_length))
            }
            else->{
                _showLoadingOTP.value = true
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phone)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(callbacks)
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            }
        }
    }

    fun verifyPhoneNumberWithCode(code: String) {
        _showLoadingOTP.value = true
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                _showLoadingOTP.value = false
                if (task.isSuccessful) {
                    _showResetFormPage.value = SingleEvents("show reset form")
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        _showToast.value = SingleEvents(app.getString(R.string.invalid_code))
                    }else{
                        _showToast.value = SingleEvents(app.getString(R.string.network_error))
                    }
                }
            }
    }

    fun changePassword(phone:String, password:String, confirmPassword:String){
        val phoneNumber = phone.replace("-","")
        when {password.isEmpty() -> {
                _errorPassword.value = SingleEvents(app.getString(R.string.err_password_empty))
            }
            password.length<5 -> {
                _errorPassword.value = SingleEvents(app.getString(R.string.err_password_length))
            }
            confirmPassword.isEmpty() -> {
                _errorConfirmPassword.value = SingleEvents(app.getString(R.string.err_password_empty))
            }
            confirmPassword.length<5 -> {
                _errorConfirmPassword.value = SingleEvents(app.getString(R.string.err_password_length))
            }
            confirmPassword!=password ->{
                _errorConfirmPassword.value = SingleEvents(app.getString(R.string.err_password_not_same))
            }
            else -> {
                _showLoadingOTP.postValue(true)
                val body = ChangePsswordReq(phoneNumber, password, confirmPassword)
                lastDisposable = authRepository.changePassword(body)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({ model ->
                        _closeResetPage.value = SingleEvents("close")
                    }, { e ->
                        handleError(e)
                        _showLoadingOTP.postValue(false)
                        _resetFailed.value = SingleEvents(getErrorMsg(e))
                    })

                lastDisposable?.let { compositeDisposable.add(it) }
            }
        }
    }
}