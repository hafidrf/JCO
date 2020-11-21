package com.jcodonuts.app.ui.base

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jcodonuts.app.R
import com.jcodonuts.app.ui.MainActivity
import java.net.URI
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {
    private val TAG = "BaseFragment"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewDataBinding: B
    lateinit var mViewModel: V

    val binding: B get() = mViewDataBinding
    val viewModel: V get() = mViewModel

    private var _isFragmentFromPaused:Boolean = false
    val isFragmentFromPaused : Boolean get() = _isFragmentFromPaused

    protected abstract fun getViewModelClass(): Class<V>

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), getStatusBarColor())

        mViewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()

        super.onCreateView(inflater, container, savedInstanceState)

        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                onBackPress()
            }
        })

    }

    open fun onBackPress(){
        findNavController().navigateUp()
    }

    override fun onResume() {
        super.onResume()
        _isFragmentFromPaused = false
    }

    override fun onPause() {
        super.onPause()
        _isFragmentFromPaused = true
    }

    fun waitForTransition(view:View){
        postponeEnterTransition()
        view.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    open fun getStatusBarColor():Int{
        return android.R.color.transparent
    }

    open fun navigateTo(link:Int){
        val uri = Uri.parse(getString(link))
        Navigation.findNavController((activity as MainActivity), R.id.nav_host_fragment)
                .navigate(uri)
    }
}