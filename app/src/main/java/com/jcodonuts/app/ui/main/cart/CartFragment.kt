package com.jcodonuts.app.ui.main.cart

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodonuts.app.R
import com.jcodonuts.app.data.local.Divider16
import com.jcodonuts.app.databinding.FragmentMainCartBinding
import com.jcodonuts.app.ui.base.BaseFragment
import com.jcodonuts.app.ui.base.InjectingNavHostFragment
import com.jcodonuts.app.ui.main.base.MainFragment
import javax.inject.Inject

class CartFragment @Inject constructor() : BaseFragment<FragmentMainCartBinding, CartViewModel>(), ControllerListener {

    private lateinit var listTest:MutableList<Divider16>
    private lateinit var  controller :CartController
    private lateinit var tempDivider:Divider16
    private var tempIndex:Int = -1

    override fun getViewModelClass(): Class<CartViewModel> {
        return CartViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_cart
    }

    override fun onViewReady(savedInstance: Bundle?) {
        controller = CartController(this)
        controller.addInterceptor {
            if(tempIndex!=-1){
                listTest[tempIndex].temp="update"
//                tempDivider?.let {
////                    listTest[tempIndex]=it
//                }
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setController(controller)
        listTest = mutableListOf(Divider16("test1"), Divider16("test2"), Divider16("test3"))
        controller.setData(listTest)
    }

    override fun onBackPress() {
        val navhost = (parentFragment as InjectingNavHostFragment)
        (navhost.parentFragment as MainFragment).backToHome()
    }

    override fun onClick(index: Int) {
//        tempDivider = listTest[index]
        tempIndex = index
//        tempDivider.temp = "update"

        controller.setData(listTest)
    }
}