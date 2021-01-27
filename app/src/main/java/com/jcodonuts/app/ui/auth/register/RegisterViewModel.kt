package com.jcodonuts.app.ui.auth.register

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.jcodonuts.app.R
import com.jcodonuts.app.ui.base.BaseViewModel
import com.jcodonuts.app.utils.SingleEvents
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val app: Application
): BaseViewModel() {

    private val _errorPhone = MutableLiveData<SingleEvents<String>>()
    val errorPhone : LiveData<SingleEvents<String>>
        get() = _errorPhone

    private val _showVerificationPage = MutableLiveData<SingleEvents<String>>()
    val showVerificationPage : LiveData<SingleEvents<String>>
        get() = _showVerificationPage

    private var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    init {
        callbacks = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, token)
                _showVerificationPage.value = SingleEvents("show")
            }

        }
    }

    fun verifyPhoneNumber(phone:String, auth:FirebaseAuth, activity: Activity){
        when{
            phone.isEmpty() -> {
                _errorPhone.value = SingleEvents(app.getString(R.string.err_phone_empty))
            }
            phone.length<10 ->{
                _errorPhone.value = SingleEvents(app.getString(R.string.err_phone_length))
            }
            else->{
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
}