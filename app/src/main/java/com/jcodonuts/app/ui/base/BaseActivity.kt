package com.jcodonuts.app.ui.base

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.LogoutEvent
import com.jcodonuts.app.ui.MainActivity
import com.jcodonuts.app.utils.SharedPreference
import dagger.android.support.DaggerAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)

        if (getLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
            onViewReady(savedInstanceState)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
            }else->{
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe // subscribe annotation in base class would not be picked up by index
    fun onLogoutEvent(event: LogoutEvent) {
        val sharedPreference = SharedPreference(this)
        sharedPreference.removeValue(SharedPreference.ACCESS_TOKEN)
        sharedPreference.removeValue(SharedPreference.REFRESH_TOKEN)
        sharedPreference.removeValue(SharedPreference.DATA_HOME)
        sharedPreference.save(SharedPreference.FROM_LOGIN, true)

        if(event.message.isNotEmpty()){
            Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
        }

        val navOptions = NavOptions.Builder().setPopUpTo(R.id.linkMainFragment, true).build()
        val uri = Uri.parse(getString(R.string.linkLogin))
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(uri, navOptions)
    }

}